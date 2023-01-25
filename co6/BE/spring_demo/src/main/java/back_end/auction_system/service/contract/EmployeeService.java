package case_study.furama_resort.service.contract;

import case_study.furama_resort.model.employee.Employee;
import case_study.furama_resort.repository.contract.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        employeeRepository.remove(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
