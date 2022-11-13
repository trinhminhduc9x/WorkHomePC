package com.furama.service.impl;

import com.furama.model.employee.Employee;
import com.furama.repository.IEmployeeRepository;
import com.furama.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public List<Employee> findAllList() {
        return this.iEmployeeRepository.findAll();
    }

    @Override
    public Page<Employee> findPageNameAll(Pageable pageable, String name) {
        return iEmployeeRepository.findAllByName(pageable,"%"+ name + "%");
    }
}
