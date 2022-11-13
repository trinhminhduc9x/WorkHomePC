package com.furama.service;


import com.furama.model.employee.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
}
