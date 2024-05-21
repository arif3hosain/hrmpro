package com.hrm.hrmpro.controller;


import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
   public String showRegistrationForm(@RequestParam(value = "registerTypeId", required = false)Long registerTypeId, Model model) {
      model.addAttribute("registerTypeId",registerTypeId);
      return "registration";
   }

   @PostMapping
   public String registerUserAccount(@ModelAttribute("user") 
                  UserRegistrationDto registrationDto,Model model) {
      try {
         userService.save(registrationDto);
      }catch(Exception e)
      {
         e.printStackTrace();
         model.addAttribute("registerTypeId",registrationDto.getRegisterTypeId());
         return "redirect:/registration?email_invalid";
      }
      return "redirect:/login?success";
   }
}