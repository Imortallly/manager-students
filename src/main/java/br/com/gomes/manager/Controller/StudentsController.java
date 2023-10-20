package br.com.gomes.manager.Controller;

import br.com.gomes.manager.Model.Students;
import br.com.gomes.manager.Repository.StudentsRepository;
import br.com.gomes.manager.Service.StudentsConsultService;
import br.com.gomes.manager.Service.StudentsValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentsController {
    @Autowired
    private StudentsValidationService studentsValidationService;
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentsConsultService studentsConsultService;

    @GetMapping("/home/")
    public String home() {
        return "home";
    }

    @GetMapping("/home/students-register/")
    public String registerStudentHome() {
        return "register-students";
    }

    @PostMapping("/home/students-register/")
    public String registerStudents(Students students, Model model) {
        boolean isValidName = studentsValidationService.validationNameStudentBD(students);
        if(isValidName) {} else {
            return studentsValidationService.errorRegisterNameStudents(model);
        }
        String numberRegister = studentsValidationService.generatorRegisterStudent();
        students.setRegistration(numberRegister);
        studentsRepository.save(students);
        return studentsValidationService.messageSaveInDataBase(model);
    }

    @GetMapping("/home/consult-students/")
    public String consultStudents() {
        return "consult-pages/consult-students";
    }

    @PostMapping("/home/consult-students/")
    public ModelAndView consultStudentsBar(@RequestParam("search-bar") String searchBar,
                                           @RequestParam("format") String format,
                                           @RequestParam("courses") String courses,
                                           @RequestParam("status") String status,
                                           @RequestParam("shift") String shift, Model model) {
        System.out.println(searchBar);
        System.out.println(format);
        System.out.println(courses);
        System.out.println(status);
        System.out.println(shift);

        return studentsConsultService.searchFilter(searchBar, format, courses, status, shift, model);
    }
}


