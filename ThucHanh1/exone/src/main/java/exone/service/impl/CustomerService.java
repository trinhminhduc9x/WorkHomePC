package exone.service.impl;


import exone.model.customer.Customer;
import exone.repository.customer.ICustomerRepository;
import exone.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public void save(Customer customer) {
        this.iCustomerRepository.save(customer);
    }

    @Override
    public Page<Customer> findPageNameEmailType(Pageable pageable, String name, String email, Integer CustomerTypeID) {
        return this.iCustomerRepository.findAllByNameAndEmailAndCustomerTypeID(pageable, name , email , CustomerTypeID );
    }


    @Override
    public Customer findById(int id) {
        return this.iCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.iCustomerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAllList() {
        return this.iCustomerRepository.findAll();
    }


}
