package case_study.furama_resort.repository.user;


import case_study.furama_resort.model.employee.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    User findById(int id);

}
