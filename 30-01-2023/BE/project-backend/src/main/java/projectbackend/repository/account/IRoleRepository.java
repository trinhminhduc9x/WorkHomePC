package projectbackend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projectbackend.model.account.Role;


import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
