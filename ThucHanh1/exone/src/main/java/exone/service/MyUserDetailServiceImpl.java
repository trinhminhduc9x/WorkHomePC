//package exone.service;
//
//
//import exone.model.MyUserDetail;
//import exone.model.User;
//import exone.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class MyUserDetailServiceImpl implements UserDetailsService {
//   @Autowired
//   private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user==null){
//            throw new UsernameNotFoundException("user name" + username+ "not found");
//        }
//        return new MyUserDetail(user);
//    }
//}
