package com.furama.service;

import com.furama.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    void save(Customer customer);
    Page<Customer> findPageNameEmailType(Pageable pageable, String name,String email,Integer CustomerTypeID);
    Customer findById(int id);
    void deleteById(int id);
    List<Customer> findAllList();


    Page<Customer> findAll(String name, Pageable pageable);
}
