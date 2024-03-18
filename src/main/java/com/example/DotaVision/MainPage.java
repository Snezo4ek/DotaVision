package com.example.DotaVision;

import com.example.DotaVision.user.User;
import com.example.DotaVision.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class MainPage {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String redirect(Principal principal){
        if (principal == null) {
            return "main-page-unlogged";
        }

        User user = userService.getByUsername(principal.getName());

        if (user.getRole().equals("ROLE_ADMIN")) {
            return "main-page-logged-admin";
        }

        return "main-page-logged-user";


    }
}
