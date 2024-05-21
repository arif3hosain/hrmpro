package com.hrm.hrmpro.controller;


import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

   private UserService userService;

   public RegistrationController(UserService userService) {
      super();
      this.userService = userService;
   }

/*
   @ModelAttribute("user")
   public UserRegistrationDto userRegistrationDto() {
      return new UserRegistrationDto();
   }
*/

   @GetMapping("/registration")
   public String showRegistrationForm(@ModelAttribute("user")
                                         UserRegistrationDto registrationDto) {
      return "registration";
   }

   @PostMapping("/registration")
   public String registerUserAccount(@ModelAttribute("user") 
                  UserRegistrationDto registrationDto) {
      try {
         userService.save(registrationDto);
      }catch(Exception e)
      {e.printStackTrace();
         return "redirect:/registration?email_invalid";
      }
      return "redirect:/login?success";
   }

   @GetMapping("/registration/applicant")
   public String applicantSignup(@ModelAttribute("user")
                                    UserRegistrationDto registrationDto) {
      return "registration";
   }

   @PostMapping("/registration/applicant")
   public String applicantSignupPost(@ModelAttribute("user")
                  UserRegistrationDto registrationDto) {
      try {
         userService.save(registrationDto);
      }catch(Exception e)
      {e.printStackTrace();
         return "redirect:/registration?email_invalid";
      }
      return "redirect:/login?success";
   }
}