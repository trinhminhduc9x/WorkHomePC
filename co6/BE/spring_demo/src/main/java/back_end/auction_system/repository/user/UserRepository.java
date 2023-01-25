package case_study.furama_resort.repository.user;

import case_study.furama_resort.model.employee.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from `user` where username=:name and status = 1 ", nativeQuery = true)
    User getUserByName(@Param("name") String name);
}
