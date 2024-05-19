package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.repos.UserRepository;
import com.hrm.hrmpro.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/18/2024
 */
@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/send-mail")
    public String showRegistrationForm(Model model) {
        model.addAttribute("error", null);
        return "send-mail";
    }

    @PostMapping("/send-mail")
    public String sendMail(@RequestParam("email")String email, Model model) {
        System.out.println(email);
        User user = userRepository.findByEmail(email);
        if(user != null){
            return "redirect:/reset-password?mail="+user.getEmail();
        }
        model.addAttribute("error", email+ " doesn't exists.");
        return "send-mail";
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("mail")String mail, Model model) {
        model.addAttribute("mail",mail);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String save(@RequestParam("email")String email,@RequestParam("password")String password) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login?reset=true";
    }

}
