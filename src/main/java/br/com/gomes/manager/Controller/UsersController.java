package br.com.gomes.manager.Controller;

import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Repository.UsersRepository;
import br.com.gomes.manager.Service.LoginValidationService;
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
    private LoginValidationService validate;

    @GetMapping("/login/")
    public String login() {
        return "login";
    }

    @PostMapping("/login/")
    public String validationLogin(@Valid Users user, Model model) {
        String redirectURL;
        if (validate.validateEmailBd(user.getUser()) && validate.validatePasswordBd(user.getUser(), user.getPassword())) {
            redirectURL = "redirect:/home";
        } else {
            redirectURL = validate.errorRegisterPasswordAndEmail(model);
            return redirectURL;
        }
        return redirectURL;
    }
}
