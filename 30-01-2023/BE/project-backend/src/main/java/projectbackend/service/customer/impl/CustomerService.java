package projectbackend.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectbackend.dto.customer.ICustomerDto;
import projectbackend.dto.customer.ICustomerStatementDto;
import projectbackend.model.customer.Customer;

import projectbackend.repository.customer.ICustomerRepository;
import projectbackend.service.customer.ICustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        customerRepository.setPassword(customer.getAccount().getAccountname(),
                passwordEncoder.encode(customer.getAccount().getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findListAll() {
        return customerRepository.findAll();
    }


    @Override
    public void saveCustomerByUser(Customer customer) {
        customerRepository.saveCustomer(customer);
    }



}