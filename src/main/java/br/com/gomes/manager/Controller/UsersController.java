package br.com.gomes.manager.Controller;

import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Repository.UsersRepository;
import br.com.gomes.manager.Service.LoginValidationService;
import br.com.gomes.manager.Service.RegisterValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LoginValidationService validateLogin;
    @Autowired
    private RegisterValidationService validateRegister;

    @GetMapping("/login/")
    public String login() {
        return "login";
    }

    @PostMapping("/login/")
    public String validationLogin(@Valid Users user, Model model) {
        String redirectURL;
        if (validateLogin.validateEmailBd(user.getUser()) && validateLogin.validatePasswordBd(user.getUser(), user.getPassword())) {
            redirectURL = "redirect:/home";
        } else {
            redirectURL = validateLogin.errorRegisterPasswordAndEmail(model);
            return redirectURL;
        }
        return redirectURL;
    }
    @GetMapping("/register/")
    public String register() {
        return "register";
    }
    @PostMapping("/register/")
    public String validationRegisterAndSave(@Valid Users user, Model model) throws Exception {
        String redirectURL = null;
        boolean validatorN = false;
        boolean validatorU = false;
        boolean valitadorCP = false;
        if(validateRegister.validationName(user.getName())) {
            validatorN = true;
            if(validateRegister.validationUsername(user.getUser())) {
                validatorU = true;
                if (validateRegister.validationConfirmPassword(user.getPassword(), user.getConfirmPassword())) {
                    valitadorCP = true;
                } else {
                    redirectURL = validateRegister.errorRegisterPasswordNotEquals(model);
                }
            } else {
                redirectURL = validateRegister.errorRegisterUsername(model);
            }
        } else {
            redirectURL = validateRegister.errorRegisterName(model);
        }
        if(validatorN && valitadorCP && validatorU) {
            redirectURL = "redirect:/login/";
            validateRegister.encryptionPassword(user);
            usersRepository.save(user);
        } else {
            return redirectURL;
        }
        return redirectURL;
    }
}
