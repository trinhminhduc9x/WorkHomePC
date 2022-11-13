package com.furama.controller;

import com.furama.dto.CustomerDto;
import com.furama.model.customer.Customer;
import com.furama.model.customer.CustomerType;
import com.furama.service.ICustomerService;
import com.furama.service.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    @GetMapping(value = "/list")
    public String viewList(Model model,
                           @PageableDefault(6) Pageable pageable,
                           @RequestParam Optional<String> name,
                           @RequestParam(required = false, defaultValue = "") String email,
                           @RequestParam(required = false, defaultValue = "") Integer CustomerTypeID) throws Exception {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        String CustomerTypeName = null;
        for (int i = 0; i < this.iCustomerTypeService.findAll().size(); i++) {
           if (this.iCustomerTypeService.findAll().get(i).getId()== CustomerTypeID){
                CustomerTypeName = this.iCustomerTypeService.findAll().get(i).getName();
           }
        }
        String keyName = name.orElse("");
        model.addAttribute("customerList", this.iCustomerService.findPageNameEmailType(pageable,keyName,email,CustomerTypeID));
        model.addAttribute("customerTypeList", this.iCustomerTypeService.findAll());
        model.addAttribute("name", keyName);
        model.addAttribute("email", email);
        model.addAttribute("customerType", CustomerTypeName);


        return "customer/list";


    }





    @GetMapping(value = "/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        model.addAttribute("customerTypeList", this.iCustomerTypeService.findAll());
        return "customer/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute @Valid CustomerDto customerDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerTypeList", this.iCustomerTypeService.findAll());
            return "customer/create";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        this.iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Create successfully!");
        return "redirect:/customer/list";
    }

    @PostMapping(value = "/remove")
    public String remove(@RequestParam int removeCustomerId,
                         RedirectAttributes redirectAttributes) {
        this.iCustomerService.deleteById(removeCustomerId);
        redirectAttributes.addFlashAttribute("msg", "Remove successfully!");
        return "redirect:/customer/list";
    }

    @GetMapping(value = "/showEdit/{id}")
    public String showEdit(@PathVariable int id,
                           Model model) {
        model.addAttribute("customerDto", this.iCustomerService.findById(id));
        model.addAttribute("customerTypeList", this.iCustomerTypeService.findAll());
        return "customer/update";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute @Valid CustomerDto customerDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerTypeList", this.iCustomerTypeService.findAll());
            return "customer/update";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        this.iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Update successfully!");
        return "redirect:/customer/list";
    }
}
