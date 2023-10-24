package br.com.gomes.manager.Service;

import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            System.out.println("usuario vazio");
        }
        Users user = usersRepository.findByUser(username);

        if (user == null) {
            System.out.println("Usuario não foi encontrado no banco de dado");
        }

        System.out.println(user);

        return User.builder()
                .username(user.getUser())
                .password(user.getPassword())
                .build();
    }
}
