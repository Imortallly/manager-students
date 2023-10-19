package br.com.gomes.manager.Service;

import br.com.gomes.manager.Model.Students;
import br.com.gomes.manager.Repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.SecureRandom;


@Service
public class StudentsValidationService {
    @Autowired
    private StudentsRepository studentsRepository;

    public boolean validationNameStudentBD(Students students) {
        Students studentName = studentsRepository.findByName(students.getName());
        return studentName == null;
    }
    public String generatorRegisterStudent() {
        SecureRandom secureRandom = new SecureRandom();
        int numberRandomSecure = secureRandom.nextInt(1001) + 1000;
        String numberRandomString = Integer.toString(numberRandomSecure);
        return validatorRegisterStudent(numberRandomString);
    }
    public String validatorRegisterStudent(String register) {
        Students isRegisterValid = studentsRepository.findByRegistration(register);
        if(isRegisterValid != null) {
            return generatorRegisterStudent();
        }
         return register;
    }

    public String errorRegisterNameStudents(Model model) {
        model.addAttribute("errorMessageNameMatch", true);
        return "register-students";
    }
    public String messageSaveInDataBase(Model model) {
        model.addAttribute("messageSaveInDataBase", true);
        return "register-students";
    }

}


