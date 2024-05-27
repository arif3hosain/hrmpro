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


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("employee") final EmployeeDTO employeeDTO, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("employee") @Valid final EmployeeDTO employeeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee/add";
        }
        employeeService.saveEmployee(employeeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("employee.create.success"));
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("employee") EmployeeDTO employeeDTO, @PathVariable(name = "id") final Long id, final Model model) {
        employeeDTO = employeeService.get(id);
        model.addAttribute("employee",employeeDTO );
        model.addAttribute("departments", departmentRepository.findAll());
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("employee") @Valid final EmployeeDTO employeeDTO, final Model model,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "employee/edit";
        }
        employeeService.update(id, employeeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("employee.update.success"));
        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("employee.delete.success"));
        return "redirect:/employees";
    }

}
