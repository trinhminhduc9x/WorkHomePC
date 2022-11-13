package ONE.service;

import ONE.model.User;


import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
}
