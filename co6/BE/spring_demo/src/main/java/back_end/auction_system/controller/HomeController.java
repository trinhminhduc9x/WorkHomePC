package case_study.furama_resort.controller;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({"/home","/"})
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }
}
