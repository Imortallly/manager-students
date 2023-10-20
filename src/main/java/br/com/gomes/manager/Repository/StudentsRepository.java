package br.com.gomes.manager.Repository;

import br.com.gomes.manager.Model.Students;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
         List<Students> findByNameContainingIgnoreCase(String name);

         Students findByName(String name);

         Students findByRegistration(String registration);
         List<Students> findAllByRegistration(String registration);

         List<Students> findByCourse(String course);

         List<Students> findByStatus(String status);

         List<Students> findByShift(String shift);
}
