package householdmanagement.controller;

import householdmanagement.dtoView.HouseholView;
import householdmanagement.service.IHouseholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/househol")
public class HouseholController {
    @Autowired
    private IHouseholService iHouseholService;

    @GetMapping("/list")
    public String showPage(Model model,
                           @PageableDefault(size = 3) Pageable pageable) {

        Page<HouseholView> householViewPage = iHouseholService.fildPageAll(pageable);
        model.addAttribute("householViewPage", householViewPage);
        return "househol/list";
    }
}
