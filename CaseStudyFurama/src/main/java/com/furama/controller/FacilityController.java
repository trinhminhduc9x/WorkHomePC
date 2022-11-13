package com.furama.controller;

import com.furama.dto.FacilityDto;
import com.furama.model.facility.Facility;
import com.furama.service.IFacilityService;
import com.furama.service.IFacilityTypeService;
import com.furama.service.IRentTypeService;
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
@RequestMapping(value = "/facility")
public class FacilityController {
    @Autowired
    private IFacilityTypeService iFacilityTypeService;

    @Autowired
    private IRentTypeService iRentTypeService;

    @Autowired
    private IFacilityService iFacilityService;

    @GetMapping(value = "/list")
    public String viewList(@RequestParam Optional<Integer> facilityTypeSelect,
                           @PageableDefault(value = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) {
        model.addAttribute("rentType", this.iRentTypeService.findAll());
        model.addAttribute("facilityType", this.iFacilityTypeService.findAll());
        model.addAttribute("facilityTypeSelect", facilityTypeSelect.orElse(0));
        if ((!facilityTypeSelect.isPresent())|| facilityTypeSelect.get() == 0){
            model.addAttribute("facilities",
                    this.iFacilityService.findAll(pageable));
        }else model.addAttribute("facilities",
                this.iFacilityService.findAllByFacilityType(facilityTypeSelect.get(), pageable));
        return "facility/list";
    }

    @GetMapping(value = "/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("facilityDto", new FacilityDto());
        model.addAttribute("rentType", this.iRentTypeService.findAll());
        model.addAttribute("facilityType", this.iFacilityTypeService.findAll());
        return "facility/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute @Valid FacilityDto facilityDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        new FacilityDto().validate(facilityDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("rentType", this.iRentTypeService.findAll());
            model.addAttribute("facilityType", this.iFacilityTypeService.findAll());
            return "facility/create";
        }
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDto, facility);
        this.iFacilityService.save(facility);
        redirectAttributes.addFlashAttribute("msg", "Create successfully!");
        return "redirect:/facility/list";
    }

    @PostMapping(value = "/remove")
    public String remove(@RequestParam int removeId,
                         RedirectAttributes redirectAttributes){
        this.iFacilityService.deleteById(removeId);
        redirectAttributes.addFlashAttribute("msg", "Delete successfully!");
        return "redirect:/facility/list";
    }

    @GetMapping(value = "/update/{id}")
    public String showUpdate(@PathVariable int id,
                             Model model){
        model.addAttribute("facilityDto", this.iFacilityService.findById(id));
        model.addAttribute("rentType", this.iRentTypeService.findAll());
        model.addAttribute("facilityType", this.iFacilityTypeService.findAll());
        return "/facility/update";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute FacilityDto facilityDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model){
        new FacilityDto().validate(facilityDto, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("rentType", this.iRentTypeService.findAll());
            model.addAttribute("facilityType", this.iFacilityTypeService.findAll());
            return "facility/update";
        }
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDto, facility);
        this.iFacilityService.save(facility);
        redirectAttributes.addFlashAttribute("msg", "Update successfully!");
        return "redirect:/facility/list";
    }
}
