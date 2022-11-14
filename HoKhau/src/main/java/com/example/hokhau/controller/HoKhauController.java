package com.example.hokhau.controller;

import com.example.hokhau.dto.SoHoKhauDto;
import com.example.hokhau.model.SoHoKhau;

import com.example.hokhau.service.ISoHoKhauService;
import com.example.hokhau.service.IThanhVienService;

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
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Controller
@RequestMapping(value = "/hoKhau")
public class HoKhauController {
    @Autowired
    private ISoHoKhauService iSoHoKhauService;

    @Autowired
    private IThanhVienService iThanhVienService;


    @GetMapping(value = "/list")
    public String viewList(Model model,
                           @PageableDefault(12) Pageable pageable,
                           @RequestParam Optional<String> name,
                           @RequestParam(required = false, defaultValue = "") String maHoKhau,
                           @RequestParam(required = false, defaultValue = "") String numberPeople) {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        String keyName = name.orElse("");



        model.addAttribute("soHoKhau", this.iSoHoKhauService.findAllByNameAndAndThanhVienAndSoHoKhau(pageable,keyName,maHoKhau,numberPeople));
        model.addAttribute("thanhVienList", this.iThanhVienService.findListAll());
        model.addAttribute("keyName", keyName);
        model.addAttribute("maHoKhau", maHoKhau);
        model.addAttribute("numberPeople", numberPeople);

        return "hoKhau/list";

    }


        @GetMapping(value = "/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("soHoKhauDto", new SoHoKhauDto());
        return "hoKhau/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute @Valid SoHoKhauDto soHoKhauDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        new SoHoKhauDto().validate(soHoKhauDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "hoKhau/create";
        }
        SoHoKhau soHoKhau = new SoHoKhau();
        BeanUtils.copyProperties(soHoKhauDto, soHoKhau);

        this.iSoHoKhauService.save(soHoKhau);
        redirectAttributes.addFlashAttribute("msg", "Create successfully!");
        return "redirect:/hoKhau/list";
    }



    @PostMapping(value = "/remove")
    public String remove(@RequestParam(name="id") int id,
                         RedirectAttributes redirectAttributes) {
        this.iSoHoKhauService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Remove successfully!");
        return "redirect:/hoKhau/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "stringArr") String stringArr,
                         RedirectAttributes redirectAttributes) {
        String[] arr = null;
        arr = stringArr.split(",");//1,1,3

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            addElement(map, Integer.parseInt(arr[i]));
        }
        for (Integer key : map.keySet()) {
            if(map.get(key)%2!=0){
                this.iSoHoKhauService.remove(key);
            }
        }
        redirectAttributes.addFlashAttribute("msg", "Remove successfully!");
        return "redirect:/hoKhau/list";

    }
    public static void addElement(Map<Integer, Integer> map, int element) {
        if (map.containsKey(element)) {
            int count = map.get(element) + 1;
            map.put(element, count);
        } else {
            map.put(element, 1);
        }
    }

    @GetMapping(value = "/showEdit/{id}")
    public String showEdit(@PathVariable int id,
                           Model model) {
        model.addAttribute("soHoKhauDto", this.iSoHoKhauService.findById(id));

        return "hoKhau/update";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute @Valid SoHoKhauDto soHoKhauDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        new SoHoKhauDto().validate(soHoKhauDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "hoKhau/update";
        }
        SoHoKhau soHoKhau = new SoHoKhau();
        BeanUtils.copyProperties(soHoKhauDto, soHoKhau);

        this.iSoHoKhauService.save(soHoKhau);
        redirectAttributes.addFlashAttribute("msg", "Update successfully!");
        return "redirect:/hoKhau/list";
    }



}
