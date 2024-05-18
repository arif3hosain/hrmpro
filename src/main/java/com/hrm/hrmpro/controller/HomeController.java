package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.repos.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =  userRepository.findByEmail(auth.getName());
        request.getSession().setAttribute("user",user);
        return "home/index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "reset", required = false)boolean reset) {
        return "login";
    }

    @GetMapping("/access-denied")
    public String denied(){
        return "access-denied";
    }

}
