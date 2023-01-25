//package case_study.furama_resort.login;
//
//import case_study.furama_resort.model.employee.Role;
//import case_study.furama_resort.model.employee.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class MyUserDetail implements UserDetails {
//
//    private User user;
//
//    public MyUserDetail(User user) {
//        this.user = user;
//    }
//
//    public MyUserDetail() {
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        for (Role role : this.user.getRoles()){
//            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return grantedAuthorityList;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
