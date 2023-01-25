package case_study.furama_resort.repository.contract;

import case_study.furama_resort.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from `employee` where status= 1", nativeQuery = true)
    List<Employee> findAll();

    @Transactional
    @Modifying
    @Query(value = "update employee set status = 0 where id = :id", nativeQuery = true)
    void remove(@Param("id") int id);
}
