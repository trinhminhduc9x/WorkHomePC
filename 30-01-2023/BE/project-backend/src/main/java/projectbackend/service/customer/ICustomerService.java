package projectbackend.service.customer;


import projectbackend.dto.customer.ICustomerDto;


import projectbackend.model.customer.Customer;


import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    void saveCustomerByUser(Customer customer);

    void saveCustomer(Customer customer);

    List<Customer> findListAll();

}