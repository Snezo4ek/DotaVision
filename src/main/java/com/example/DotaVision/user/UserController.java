package com.example.DotaVision.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {


    UserService userService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(User user) {
        return "user-create";

    }

    @PostMapping("/register")
    public String register(User user) {
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("users/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteItem(@PathVariable("id") Long id, Principal principal){
        User user = userService.getById(id);

        User adminUser = userService.getByUsername(principal.getName());

        if (Objects.equals(user.getId(), adminUser.getId())) {
            return "403-template";
        }

        userService.deleteUserById(id);
        return "redirect:/users";
    }
}