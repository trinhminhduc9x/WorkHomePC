package casestudy.controller;

import casestudy.model.customer.Customer;
import casestudy.model.customer.CustomerType;
import casestudy.service.ICustomerService;
import casestudy.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;
//
//    @GetMapping("/list")
//    public String goPage(Model model,
//                         @PageableDefault(6) Pageable pageable,
//                         @RequestParam Optional<String> name) throws Exception {
//        for (Sort.Order order: pageable.getSort()) {
//            model.addAttribute("sortValue", order.getProperty());
//        }
//        String keyName = name.orElse("");
//
//        Page<Customer> customerPage = customerService.findPageNameAll(pageable,keyName);
//        List<CustomerType> customerTypeList = customerTypeService.findListAll();
//        model.addAttribute("customerTypeList",customerTypeList);
//        model.addAttribute("customerPage", customerPage);
//        model.addAttribute("name", keyName);
//        if (customerPage.isEmpty()) {
//            throw new Exception();
//        }else {
//            return "/customer/list";
//        }
//    }

//    @GetMapping("/list")
//    public String goPage(Model model,
//                         @PageableDefault(6) Pageable pageable,
//                         @RequestParam Optional<String> name,
//                         @RequestParam(required = false, defaultValue = "") String email,
//                         @RequestParam(required = false, defaultValue = "") String customerTypeName)throws Exception {
//        for (Sort.Order order : pageable.getSort()) {
//            model.addAttribute("sortValue", order.getProperty());
//        }
//        String keyName = name.orElse("");
//
//        Page<Customer> customerPage = customerService.findPageNameEmailCustomerType(pageable,keyName,email,customerTypeName);
//
//        List<CustomerType> customerTypeList = customerTypeService.findListAll();
//        model.addAttribute("customerTypeList", customerTypeList);
//        model.addAttribute("customerPage", customerPage);
//        model.addAttribute("name", keyName);
//        model.addAttribute("email", email);
//        model.addAttribute("customerTypeName", customerTypeName);
//        if (customerPage.isEmpty()) {
//            throw new Exception();
//        } else {
//            return "/customer/list";
//        }
//    }
//
//    @GetMapping("/list")
//    public String goPage(Model model,
//                         @PageableDefault(6) Pageable pageable,
//                         @RequestParam Optional<String> name,
//                         @RequestParam(required = false, defaultValue = "") String email)throws Exception {
//        for (Sort.Order order : pageable.getSort()) {
//            model.addAttribute("sortValue", order.getProperty());
//        }
//        String keyName = name.orElse("");
//
//        Page<Customer> customerPage = customerService.findPageNameEmail(pageable,keyName,email);
//
//        List<CustomerType> customerTypeList = customerTypeService.findListAll();
//        model.addAttribute("customerTypeList", customerTypeList);
//        model.addAttribute("customerPage", customerPage);
//        model.addAttribute("name", keyName);
//        model.addAttribute("email", email);
//        if (customerPage.isEmpty()) {
//            throw new Exception();
//        } else {
//            return "/customer/list";
//        }
//    }


    @GetMapping("/list")
    public String goPage(Model model,
                         @PageableDefault(6) Pageable pageable,
                         @RequestParam Optional<String> name,
                         @RequestParam(required = false, defaultValue = "") String email,
                         @RequestParam(required = false, defaultValue = "") String CustomerTypeID)throws Exception {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        String keyName = name.orElse("");

        Page<Customer> customerPage = customerService.findPageNameEmailType(pageable,keyName,email,CustomerTypeID);

        List<CustomerType> customerTypeList = customerTypeService.findListAll();
        model.addAttribute("customerTypeList", customerTypeList);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("name", keyName);
        model.addAttribute("email", email);
//        model.addAttribute("CustomerTypeID", customerTypeList);
        if (customerPage.isEmpty()) {
            throw new Exception();
        } else {
            return "/customer/list";
        }
    }


    @GetMapping("/create")
    public String create(Model model) {

        List<CustomerType> customerTypeList = customerTypeService.findListAll();


        model.addAttribute("customerTypeList", customerTypeList);


        model.addAttribute("customer", new Customer());


        return "/customer/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer) {

        customerService.save(customer);

        return "redirect:/customer/list";
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
        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Integer id) {
        customerService.removeNew(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/deleteNew")
    public String deleteNew(@RequestParam(name = "id") Integer id) {
        customerService.remove(id);
        return "redirect:/customer/list";
    }

    @ExceptionHandler(value = Exception.class)
    public String error() {
        return "/error";
    }


}
