package case_study.furama_resort.service.contract;

import case_study.furama_resort.model.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    void save(Employee employee);

    Optional<Employee> findById(int id);

    void remove(int id);

    List<Employee> findAll();
}
