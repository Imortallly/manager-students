package br.com.gomes.manager.Controller;

import br.com.gomes.manager.Model.Students;
import br.com.gomes.manager.Repository.StudentsRepository;
import br.com.gomes.manager.Service.StudentsValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentsController {
    @Autowired
    private StudentsValidationService studentsValidationService;
    @Autowired
    private StudentsRepository studentsRepository;

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
}


