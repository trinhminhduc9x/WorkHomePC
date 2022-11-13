package exone.controller;


import exone.dto.customer.CustomerDto;
import exone.model.customer.Customer;
import exone.model.customer.CustomerType;
import exone.service.customer.ICustomerService;
import exone.service.customer.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping("/")
    public String goPage(Model model,
                         @PageableDefault(6) Pageable pageable,
                         @RequestParam Optional<String> name,
                         @RequestParam(required = false, defaultValue = "") String email,
                         @RequestParam(required = false, defaultValue = "") String CustomerTypeID) throws Exception {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        String keyName = name.orElse("");

        Page<Customer> customerPage = customerService.findPageNameEmailType(pageable, keyName, email, CustomerTypeID);

        List<CustomerType> customerTypeList = customerTypeService.findListAll();

        model.addAttribute("customerTypeList", customerTypeList);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("CustomerTypeID", CustomerTypeID);
        model.addAttribute("name", keyName);
        model.addAttribute("email", email);

//        báo lỗi nếu list rỗng
//
//        if (customerPage.isEmpty()) {
//            throw new Exception();
//        } else {
//            return "/customer/list";
//        }

        return "/customer/list";
    }


    @GetMapping("/create")
    public String create(Model model) {

        List<CustomerType> customerTypeList = customerTypeService.findListAll();
        model.addAttribute("customerTypeList", customerTypeList);


        model.addAttribute("customerDto", new CustomerDto());


        return "/customer/create";
    }

    @PostMapping("/save")
    public String save(@Validated
                       @ModelAttribute("customerDto") CustomerDto customerDto
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {

            List<CustomerType> customerTypeList = customerTypeService.findListAll();
            model.addAttribute("customerTypeList", customerTypeList);

            return "/customer/create";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            this.customerService.save(customer);
            redirectAttributes.addFlashAttribute("message", "Create form" + customer.getName() + "ok");
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("customerTypeList", customerTypeService.findListAll());
        return "/customer/edit";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer);
        return "redirect:/customer/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Integer id) {
        customerService.remove(id);
        return "redirect:/customer/";
    }


    @ExceptionHandler(value = Exception.class)
    public String error() {
        return "/error";
    }
}
