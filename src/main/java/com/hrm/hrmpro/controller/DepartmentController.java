package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.DepartmentDTO;
import com.hrm.hrmpro.service.DepartmentService;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "department/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("department") final DepartmentDTO departmentDTO) {
        return "department/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("department") @Valid final DepartmentDTO departmentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "department/add";
        }
        departmentService.create(departmentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("department.create.success"));
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("department", departmentService.get(id));
        return "department/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("department") @Valid final DepartmentDTO departmentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "department/edit";
        }
        departmentService.update(id, departmentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("department.update.success"));
        return "redirect:/departments";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        departmentService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("department.delete.success"));
        return "redirect:/departments";
    }

}
