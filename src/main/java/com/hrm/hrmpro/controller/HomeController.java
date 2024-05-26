package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.domain.Organization;
import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.repos.OrgRepo;
import com.hrm.hrmpro.repos.UserRepository;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrgRepo orgRepo;

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

    @GetMapping("/organization")
    public String organization(@ModelAttribute("org")Organization org, Model model) {
        org = orgRepo.findAll().get(0);
        model.addAttribute("org", org);
        return "organization";
    }

    @PostMapping("/organization")
    public String save(@ModelAttribute("org")Organization org, final RedirectAttributes redirectAttributes) {
        orgRepo.save(org);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Data has been updated."));
        return "redirect:/organization";
    }

}
