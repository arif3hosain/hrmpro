package com.hrm.hrmpro.controller;


import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

   private UserService userService;

   public RegistrationController(UserService userService) {
      super();
      this.userService = userService;
   }

   @ModelAttribute("user")
   public UserRegistrationDto userRegistrationDto() {
      return new UserRegistrationDto();
   }

   @GetMapping
   public String showRegistrationForm() {
      return "registration";
   }

   @PostMapping
   public String registerUserAccount(@ModelAttribute("user") 
                  UserRegistrationDto registrationDto) {

      try {
         userService.save(registrationDto);
      }catch(Exception e)
      {e.printStackTrace();
         return "redirect:/registration?email_invalid";
      }
      return "redirect:/registration?success";
   }
}