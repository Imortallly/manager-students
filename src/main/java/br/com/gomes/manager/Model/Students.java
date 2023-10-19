package br.com.gomes.manager.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String course;
    String registration;
    String name;
    String status;
    String shift;
}
