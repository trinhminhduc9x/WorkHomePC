package com.furama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuramaController {

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }
}
