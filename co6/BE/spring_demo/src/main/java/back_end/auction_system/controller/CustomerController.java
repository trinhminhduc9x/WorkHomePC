package case_study.furama_resort.controller;

import case_study.furama_resort.model.customer.Customer;
import case_study.furama_resort.model.customer.CustomerType;
import case_study.furama_resort.model.dto.CustomerDto;
import case_study.furama_resort.model.dto.contract.ContractDetailAttachFacilityDto;
import case_study.furama_resort.service.contract.IContractDetailService;
import case_study.furama_resort.service.customer.ICustomerService;
import case_study.furama_resort.service.customer.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping
    public String search(@RequestParam(value = "searchName", defaultValue = "") String name,
                         @RequestParam(value = "searchEmail", defaultValue = "") String email,
                         @RequestParam(value = "searchCustomerType", defaultValue = "") String typeName,
                         Model model,
                         @PageableDefault(value = 3) Pageable pageable) {
        Page<Customer> customers = customerService.findByNameAndEmailAndCustomerType(name, email, typeName, pageable);
        model.addAttribute("customers", customers);
        model.addAttribute("searchName", name);
        model.addAttribute("searchEmail", email);
        model.addAttribute("searchCustomerType", typeName);
        return "customers/list";
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<Customer> view(@PathVariable(value = "id") int id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (!optionalCustomer.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<Customer> remove(@PathVariable(value = "id") int id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (!optionalCustomer.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public String remove(@RequestParam(value = "id") int id, RedirectAttributes redirect) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (!optionalCustomer.isPresent()) {
            redirect.addFlashAttribute("message", "Customer not found!");
        } else {
            customerService.remove(id);
            redirect.addFlashAttribute("message", "Customer removed!");
        }
        return "redirect:/customers";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "customers/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(value = "customerDto") CustomerDto customerDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirect,
                         Model model) {

        new CustomerDto().validate(customerDto, bindingResult);
        if (!bindingResult.hasFieldErrors()) {
            Customer customer = new Customer();
            customerDto.dateFormat();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.save(customer);
            redirect.addFlashAttribute("message", "Customer created successfully");
            return "redirect:/customers/create";
        }
        model.addAttribute("message", "Customer creation failed");
        model.addAttribute("customer", customerDto);
        return "customers/create";

    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirect) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (!optionalCustomer.isPresent()) {
            redirect.addFlashAttribute("message", "Customer not found!");
            return "redirect:/customers";
        }
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(optionalCustomer.get(), customerDto);
        model.addAttribute("customerDto", customerDto);
        return "customers/edit";
    }

    @PostMapping("/edit")
    public String update(@Validated @ModelAttribute(value = "customerDto") CustomerDto customerDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirect) {
        Optional<Customer> optionalCustomer = customerService.findById(customerDto.getId());
        if (!optionalCustomer.isPresent()) {
            redirect.addFlashAttribute("message", "Customer not found!");
            return "redirect:/customers";
        }
        if (!bindingResult.hasErrors()) {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.save(customer);
            redirect.addFlashAttribute("message", "Customer updated successfully");
            return "redirect:/customers";
        }
        redirect.addFlashAttribute("message", "Customer update failed");
        return "/customers/edit";
    }

    @GetMapping("/using")
    public String searchCustomerUsingService(@RequestParam(value = "searchName", defaultValue = "") String name,
                                             @RequestParam(value = "searchEmail", defaultValue = "") String email,
                                             @RequestParam(value = "searchCustomerType", defaultValue = "") String typeName,
                                             Model model,
                                             @PageableDefault(value = 3) Pageable pageable) {
        Page<Customer> customers = customerService.findCustomerUsingService(name, email, typeName, pageable);
        model.addAttribute("customers", customers);
        model.addAttribute("searchName", name);
        model.addAttribute("searchEmail", email);
        model.addAttribute("searchCustomerType", typeName);
        return "customers/customers_use_service";
    }

    @GetMapping("/{id}/using")
    public ResponseEntity<List<ContractDetailAttachFacilityDto>> getContractDetails(@PathVariable int id) {
        List<ContractDetailAttachFacilityDto> contractDetailAttachFacilityDtos = contractDetailService.findByCustomer(id);
        System.out.println(contractDetailAttachFacilityDtos.toString());
        if (contractDetailAttachFacilityDtos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contractDetailAttachFacilityDtos, HttpStatus.OK);
    }

    @ModelAttribute("customerTypes")
    public List<CustomerType> getCustomerTypes() {
        return customerTypeService.getCustomerTypes();
    }
}
