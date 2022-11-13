package exone.service.impl;


import exone.model.customer.CustomerType;
import exone.repository.customer.ICustomerTypeRepository;
import exone.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository iCustomerTypeRepository;


    @Override
    public List<CustomerType> findAll() {
        return this.iCustomerTypeRepository.findAll();
    }
}
