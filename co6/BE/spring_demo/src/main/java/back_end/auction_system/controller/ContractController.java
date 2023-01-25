package case_study.furama_resort.controller;

import case_study.furama_resort.model.contract.AttachFacility;
import case_study.furama_resort.model.contract.Contract;
import case_study.furama_resort.model.contract.ContractDetail;
import case_study.furama_resort.model.dto.contract.ContractDetailAttachFacilityDto;
import case_study.furama_resort.model.customer.Customer;
import case_study.furama_resort.model.dto.contract.ContractDto;
import case_study.furama_resort.model.employee.Employee;
import case_study.furama_resort.model.facilities.Facility;
import case_study.furama_resort.service.contract.IAttachFacilityService;
import case_study.furama_resort.service.contract.IContractDetailService;
import case_study.furama_resort.service.contract.IContractService;
import case_study.furama_resort.service.contract.IEmployeeService;
import case_study.furama_resort.service.customer.ICustomerService;
import case_study.furama_resort.service.facility.IFacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.Function;

@CrossOrigin("*")
@Controller
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IAttachFacilityService attachFacilityService;

    @Autowired
    private IContractDetailService contractDetailService;


    @GetMapping
    public String list(Model model,
                       @PageableDefault(value = 3) Pageable pageable) {
        Page<Contract> contractPage = contractService.findAll(pageable);

        Page<ContractDto> contractDtoPage = contractPage.map(new Function<Contract, ContractDto>() {
            @Override
            public ContractDto apply(Contract contract) {
                ContractDto contractDto = new ContractDto();
                BeanUtils.copyProperties(contract, contractDto);
                contractDto.getTotalCost();
                return contractDto;
            }
        });
        model.addAttribute("contracts", contractDtoPage);
        model.addAttribute("contractDto", new ContractDto());
        return "contracts/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contractDto", new ContractDto());
        return "contracts/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(value = "contractDto") ContractDto contractDto,
                         BindingResult bindingResult,
                         @ModelAttribute(value = "attachFacilities") List<AttachFacility> attachFacilities,
                         RedirectAttributes redirect,
                         Model model) {

        new ContractDto().validate(contractDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            Contract contract = new Contract();
            BeanUtils.copyProperties(contractDto, contract);
            contractService.save(contract);

            for (int i = 0; i < contractDto.getQuantities().size(); i++) {
                Integer quantity = contractDto.getQuantities().get(i);
                if (quantity != null) {
                    ContractDetail contractDetail = new ContractDetail();
                    contractDetail.setQuantity(quantity);
                    contractDetail.setAttachFacility(attachFacilities.get(i));
                    contractDetail.setContract(contract);
                    contractDetailService.save(contractDetail);
                }
            }
            redirect.addFlashAttribute("message", "Contract created successfully");
            return "redirect:/contracts/create";
        } else {
            model.addAttribute("message", "Contract creation failed");
            model.addAttribute("contractDto", contractDto);
            return "contracts/create";
        }

    }

    @GetMapping("/{id}/attachFacilities")
    public ResponseEntity<List<ContractDetailAttachFacilityDto>> getContractDetails(@PathVariable int id) {
        List<ContractDetailAttachFacilityDto> contractDetailAttachFacilityDtos = contractDetailService.findByContractId(id);
        System.out.println(contractDetailAttachFacilityDtos.toString());
        if (contractDetailAttachFacilityDtos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contractDetailAttachFacilityDtos, HttpStatus.OK);
    }

    @PostMapping("/{id}/attachFacilities")
    public String addAttachFacilities(@PathVariable int id,
                                      @Validated @ModelAttribute(value = "contractDto") ContractDto contractDto,
                                      BindingResult bindingResult,
                                      @ModelAttribute(value = "attachFacilities") List<AttachFacility> attachFacilities,
                                      RedirectAttributes redirect) {
        if (!bindingResult.hasErrors()) {
            for (int i = 0; i < contractDto.getQuantities().size(); i++) {
                Integer quantity = contractDto.getQuantities().get(i);
                if (quantity != null) {
                    ContractDetail contractDetail = new ContractDetail();
                    contractDetail.setQuantity(quantity);
                    contractDetail.setAttachFacility(attachFacilities.get(i));
                    contractDetail.setContract(contractService.findById(id).get());
                    contractDetailService.save(contractDetail);
                }
            }
            redirect.addFlashAttribute("message", "Attach Facility added successfully");
        } else {
            redirect.addFlashAttribute("message", "Attach Facility add failed");
        }
        return "redirect:/contracts";

    }


    //    @GetMapping("/{id}/delete")
//    public ResponseEntity<Contract> remove(@PathVariable(value = "id") int id) {
//        Optional<Contract> optionalContract = contractService.findById(id);
//        if (!optionalContract.isPresent()) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(optionalContract.get(), HttpStatus.OK);
//    }

//    @PostMapping("/delete")
//    public String remove(@RequestParam(value = "id") int id, RedirectAttributes redirect) {
//        Optional<Contract> optionalContract = contractService.findById(id);
//        if (!optionalContract.isPresent()) {
//            redirect.addFlashAttribute("message", "Contract not found!");
//        } else {
//            contractService.remove(id);
//            redirect.addFlashAttribute("message", "Contract removed!");
//        }
//        return "redirect:/contracts";
//    }

//    @GetMapping("/{id}/edit")
//    public String update(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirect) {
//        Optional<Contract> optionalContract = contractService.findById(id);
//        if (!optionalContract.isPresent()) {
//            redirect.addFlashAttribute("message", "Contract not found!");
//            return "redirect:/contracts";
//        }
//        ContractDto contractDto = new ContractDto();
//        BeanUtils.copyProperties(optionalContract.get(), contractDto);
//        model.addAttribute("contractDto", contractDto);
//        return "contracts/edit";
//    }

//    @PostMapping("/edit")
//    public String update(@Validated @ModelAttribute(value = "contractDto") ContractDto contractDto,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirect) {
//        Optional<Contract> optionalContract = contractService.findById(contractDto.getId());
//        if (!optionalContract.isPresent()) {
//            redirect.addFlashAttribute("message", "Contract not found!");
//            return "redirect:/contracts";
//        }
//        if (!bindingResult.hasErrors()) {
//            Contract contract = new Contract();
//            BeanUtils.copyProperties(contractDto, contract);
//            contractService.save(contract);
//            redirect.addFlashAttribute("message", "Contract updated successfully");
//            return "redirect:/contracts";
//        }
//        redirect.addFlashAttribute("message", "Contract update failed");
//        return "/contracts/edit";
//    }

    //    @GetMapping("/{id}/delete")
//    public String remove(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirect) {
//        Optional<Customer> optionalCustomer = customerService.findById(id);
//        if (!optionalCustomer.isPresent()) {
//            redirect.addFlashAttribute("message", "Customer not found!");
//            return "redirect:/customers";
//        }
//        model.addAttribute("customer", optionalCustomer.get());
//        return "customers/list";
//    }

    @ModelAttribute("customers")
    public List<Customer> getCustomerList() {
        return customerService.findAllCustomer();
    }

    @ModelAttribute("facilities")
    public List<Facility> getFacilityList() {
        return facilityService.findAll();
    }

    @ModelAttribute("employees")
    public List<Employee> getEmployeeList() {
        return employeeService.findAll();
    }

    @ModelAttribute("attachFacilities")
    public List<AttachFacility> getAttachFacilityList() {
        return attachFacilityService.findAll();
    }


}
