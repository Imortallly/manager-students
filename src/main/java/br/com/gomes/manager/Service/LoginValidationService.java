package br.com.gomes.manager.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginValidationService {
    @Autowired
    private UsersRepository usersrepository;

    public boolean validateEmailBd(String username) {
        var getUser = usersrepository.findByUser(username);
        if(getUser != null) {
            System.out.println("Usuário encontrado");
        }
        return true;
    }
    public boolean validatePasswordBd(String username, String password){
        boolean returnOfVerify;
        try {
            var idUsername = usersrepository.findIdByUser(username);
            var hashedPasswordFromDB = usersrepository.findPasswordById(idUsername);
            returnOfVerify = BCrypt.verifyer().verify(password.toCharArray(), hashedPasswordFromDB).verified;
        } catch (NullPointerException e) {
            returnOfVerify = false;
        }
        return returnOfVerify;
    }

    public String errorRegisterPasswordAndEmail(Model model) {
        model.addAttribute("errorMessagePasswordAndEmail", true);
        return "login";
    }

    //Somente Cadastro
    private boolean encryptionPassword(Users user) throws Exception {
        try {
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(bcryptHashString);
        } catch (Exception e) {
            throw new Exception("Erro na criptográfia");
        }
        return true;
    }
}
