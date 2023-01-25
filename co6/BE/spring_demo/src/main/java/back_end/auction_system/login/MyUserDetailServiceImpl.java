//package case_study.furama_resort.login;
//
//import case_study.furama_resort.model.employee.User;
//import case_study.furama_resort.repository.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class MyUserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.getUserByName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User name: " + username + "not found");
//        }
//        return new MyUserDetail(user);
//    }
//}
