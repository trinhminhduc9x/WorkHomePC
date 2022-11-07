package casestudy.controller;

import casestudy.model.contract.Contract;
import casestudy.model.contract.ContractDetail;
import casestudy.model.customer.Customer;
import casestudy.model.employee.Employee;
import casestudy.model.facility.Facility;
import casestudy.model.facility.FacilityType;
import casestudy.model.facility.RentType;
import casestudy.service.contract.IAttachFacilityService;
import casestudy.service.contract.IContracDetailService;
import casestudy.service.contract.IContractService;
import casestudy.service.customer.ICustomerService;
import casestudy.service.employee.IEmployeeService;
import casestudy.service.facility.IFacilityService;
import casestudy.service.facility.IFacilityTypeService;
import casestudy.service.facility.IRentTypeService;
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
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;
    @Autowired
    private IContracDetailService contracDetailService;

    @Autowired
    private IAttachFacilityService iAttachFacilityService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/list")
    public String goPage(Model model,
                         @PageableDefault(6) Pageable pageable,
                         @RequestParam Optional<String> name) throws Exception {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        String keyName = name.orElse("");

        Page<Contract> contractPage = contractService.findPageAll(pageable);

        model.addAttribute("contractPage", contractPage);
        model.addAttribute("name", keyName);
        if (contractPage.isEmpty()) {
            throw new Exception();
        } else {
            return "/contract/list";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {

        List<Customer> customerList = customerService.findListAll();
        List<Facility> facilityList = facilityService.findListAll();
        List<Employee> employeeList = employeeService.findListAll();

        model.addAttribute("customerList", customerList);
        model.addAttribute("facilityList", facilityList);
        model.addAttribute("employeeList", employeeList);

        model.addAttribute("contract", new Contract());

        return "/contract/create";
    }

    @GetMapping("/createNew")
    public String createNew(Model model,
                            @RequestParam(name = "quantity") String quantity) {


        ContractDetail contractDetail = new ContractDetail();

        List<Contract> contractList = contractService.findListAll();

        model.addAttribute("contractList", contractList);

        model.addAttribute("contractDetail", contractDetail);



        contractDetail.setQuantity(quantity);
        contracDetailService.save(contractDetail);
        return "/contract/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Contract contract) {

        contractService.save(contract);

        return "redirect:/contract/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {

        List<Customer> customerList = customerService.findListAll();
        List<Facility> facilityList = facilityService.findListAll();
        List<Employee> employeeList = employeeService.findListAll();

        model.addAttribute("customerList", customerList);
        model.addAttribute("facilityList", facilityList);
        model.addAttribute("employeeList", employeeList);

        model.addAttribute("contract", contractService.findById(id));

        return "/contract/edit";
    }

    @PostMapping("/update")
    public String update(Contract contract) {
        contractService.update(contract);
        return "redirect:/contract/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Integer id) {
        contractService.remove(id);
        return "redirect:/contract/list";
    }


    @ExceptionHandler(value = Exception.class)
    public String error() {
        return "/error";
    }


}
