package projectbackend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projectbackend.dto.customer.CustomerDto;

import projectbackend.model.customer.Customer;
import projectbackend.service.customer.ICustomerService;


import javax.validation.Valid;
import java.util.List;



@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private ICustomerService iCustomerService;
    

    @PostMapping("/add")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customer.getAccount().setPassword(new BCryptPasswordEncoder().encode(customerDto.getAccount().getPassword()));
        iCustomerService.saveCustomerByUser(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> showList() {
        List<Customer> customerList = iCustomerService.findListAll();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

}