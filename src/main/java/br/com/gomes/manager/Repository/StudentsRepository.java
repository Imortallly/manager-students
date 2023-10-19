package br.com.gomes.manager.Repository;

import br.com.gomes.manager.Model.Students;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
         Students findByName(String user);

         Students findByRegistration(String registration);

         Students findByCourse(String course);

         Students findByStatus(String status);

         Students findByShift(String shift);
}
