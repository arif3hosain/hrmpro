package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.EmployeeDTO;
import com.hrm.hrmpro.repos.DepartmentRepository;
import com.hrm.hrmpro.service.EmployeeService;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/10/2024
 */

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        EmployeeDTO dto = employeeService.get(id);
        model.addAttribute("employee",dto );
        model.addAttribute("departments", departmentRepository.findAll());
        return "employee/profile";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
                       @ModelAttribute("employee") @Valid final EmployeeDTO employeeDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee/profile";
        }
        employeeService.update(id, employeeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("profile has been updatd."));
        return "redirect:/";
    }
}
