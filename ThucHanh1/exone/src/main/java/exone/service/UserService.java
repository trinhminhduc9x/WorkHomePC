package exone.service;


import exone.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
}
