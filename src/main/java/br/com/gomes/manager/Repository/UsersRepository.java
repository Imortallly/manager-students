package br.com.gomes.manager.Repository;

import br.com.gomes.manager.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


    Users findByUser(String user);

    @Query("SELECT u.password FROM Users u WHERE u.id = :userId")
    String findPasswordById(@Param("userId") Long userId);

    @Query("SELECT u.id FROM Users u WHERE u.user = :user")
    Long findIdByUser(@Param("user") String user);

    Users findByName(String user);
}
