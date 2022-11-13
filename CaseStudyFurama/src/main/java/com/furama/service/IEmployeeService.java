package com.furama.service;

import com.furama.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAllList();

    Page<Employee> findPageNameAll(Pageable pageable, String name);
}
