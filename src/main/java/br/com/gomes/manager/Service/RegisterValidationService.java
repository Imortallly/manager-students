package br.com.gomes.manager.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Service
public class RegisterValidationService {

    @Autowired
    private UsersRepository usersRepository;
    public boolean validationName(String name) {
        Users validateName = usersRepository.findByName(name);
        boolean validator = false;
        if(validateName != null) {
            return validator;
        }
        return true;
    }
    public boolean validationUsername(String username) {
        Users validateUser = usersRepository.findByUser(username);
        boolean validator = false;
        if(validateUser != null) {
            return validator;
        }
        return true;
    }

    public boolean validationConfirmPassword(String password, String confirmPassword) {
        boolean validator = false;
        if(!Objects.equals(password, confirmPassword)) {
            return validator;
        }
        return true;
    }
    public boolean encryptionPassword(Users user) throws Exception {
        try {
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(bcryptHashString);
        } catch (Exception e) {
            throw new Exception("Erro na criptográfia");
        }
        return true;
    }
    public String errorRegisterName(Model model) {
        model.addAttribute("errorMessageName", true);
        return "register";
    }
    public String errorRegisterUsername(Model model) {
        model.addAttribute("errorMessageUsername", true);
        return "register";
    }
    public String errorRegisterPasswordNotEquals(Model model) {
        model.addAttribute("errorMessagePasswordConfirm", true);
        return "register";
    }

}
