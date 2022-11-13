package com.furama.controller;

import com.furama.model.contract.Contract;
import com.furama.model.contract.ContractDetail;
import com.furama.model.contract.ContractTemp;
import com.furama.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IFacilityService iFacilityService;

    @Autowired
    private IAttachFacilityService iAttachFacilityService;

    @GetMapping(value = "/list")
    public String viewList() {
        return "contract/list";
    }

    @GetMapping(value = "/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("contractTempObj", new ContractTemp());
        model.addAttribute("contractDetailObj", new ContractDetail());
        model.addAttribute("attachFacilityList", this.iAttachFacilityService.findAll());
        model.addAttribute("customerList", this.iCustomerService.findAllList());
        model.addAttribute("employeeList", this.iEmployeeService.findAllList());
        model.addAttribute("facilityList", this.iFacilityService.findAllList());
        return "contract/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Contract contractObj,
                         RedirectAttributes redirectAttributes){
        this.iContractService.save(contractObj);
        return "redirect:/contract/list";
    }
}
