package br.com.gomes.manager.Controller;

import br.com.gomes.manager.Model.Students;
import br.com.gomes.manager.Repository.StudentsRepository;
import br.com.gomes.manager.Service.StudentsConsultService;
import br.com.gomes.manager.Service.StudentsValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
        if (isValidName) {
        } else {
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
        return studentsConsultService.searchFilter(searchBar, format, courses, status, shift, model);
    }

    @GetMapping("/home/consult-students/remove/{id}")
    public String deleteStudents(@PathVariable("id") Long id, Model model) {
        studentsRepository.deleteById(id);
        return "redirect:/home/consult-students/";
    }

    @GetMapping("/home/consult-students/edit-register-students/{id}")
    public ModelAndView editRegisterStudents(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consult-pages/edit-register-students");
        Optional<Students> students = studentsRepository.findById(id);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @PostMapping("/home/consult-students/edit-register-students/")
    public ModelAndView updateStudent(Students updatedStudent) {
        ModelAndView modelAndView = new ModelAndView();
        studentsRepository.save(updatedStudent);
        modelAndView.setViewName("redirect:/home/consult-students/");
        return modelAndView;
    }

    @GetMapping("/home/consult-students/detais-students/{id}")
    public ModelAndView detailsRegisterStudents(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consult-pages/details-student");
        Optional<Students> students = studentsRepository.findById(id);
        System.out.println(students);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}



