package com.example.DotaVision;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainPage {
    @GetMapping("/")
    public String redirect(){
        return "main-page";
    }
}
