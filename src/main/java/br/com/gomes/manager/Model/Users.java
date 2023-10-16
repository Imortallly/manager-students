package br.com.gomes.manager.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String password;
    @Email
    private String email;

    public Users() {

    }
}
