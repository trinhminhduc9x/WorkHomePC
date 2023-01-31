package com.c0722g1repobe.controller.customer;

import com.c0722g1repobe.dto.customer.CustomerDto;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.entity.customer.Customer;
import com.c0722g1repobe.jwt.jwt.JwtProvider;
import com.c0722g1repobe.service.account.impl.RoleService;
import com.c0722g1repobe.service.account.impl.UserService;
import com.c0722g1repobe.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private ICustomerService iCustomerService;

    
    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */

//    @PostMapping("/add")
//    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getFieldErrors(),
//                    HttpStatus.BAD_REQUEST);
//        }
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(customerDto, customer);
//        customer.getAccount().setEncryptPassword(new BCryptPasswordEncoder().encode(customerDto.getAccount().getEncryptPassword()));
//        iCustomerService.saveCustomerByUser(customer);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @PostMapping("/add")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid Customer customer) {

        customer.getAccount().setEncryptPassword(new BCryptPasswordEncoder().encode(customer.getAccount().getEncryptPassword()));
        iCustomerService.saveCustomerByUser(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

//    @PostMapping(value = "/signup")
//    public ResponseEntity<?> register(@Valid @RequestBody Customer customer) {
//        if (userService.existsByUsername(customer.getAccount().getUsernameAccount())) {
//            return new ResponseEntity<>(new ResponseMessage("The username is existed"), HttpStatus.OK);
//        }
//        if (userService.existsByEmail(customer.getEmailCustomer())) {
//            return new ResponseEntity<>(new ResponseMessage("The email is existed"), HttpStatus.OK);
//        }
//        Account account = new Account(customer.getNameCustomer(), customer.getAccount().getUsernameAccount(), customer.getEmailCustomer(), passwordEncoder.encode(customer.getAccount().getEncryptPassword()));
//        Set<Role> strRole = customer.getAccount().getRoles();
//        Set<Role> roles = new HashSet<>();
//        strRole.forEach(role -> {
//            if ("admin".equals(role)) {
//                Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
//                roles.add(adminRole);
//            } else if ("employee".equals(role)) {
//                Role employeeRole = roleService.findByName(RoleName.EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
//                roles.add(employeeRole);
//            } else {
//                Role customerRole = roleService.findByName(RoleName.CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
//                roles.add(customerRole);
//            }
//        });
//        account.setRoles(roles);
//        userService.save(account);
//        return new ResponseEntity<>(new ResponseMessage("Create success!"), HttpStatus.OK);
//    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> showList() {
        List<Customer> customerList = iCustomerService.findListAll();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

}