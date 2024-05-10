package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.CompensationDTO;
import com.hrm.hrmpro.repos.EmployeeRepository;
import com.hrm.hrmpro.service.CompensationService;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
@RequestMapping("/compensations")
public class CompensationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final CompensationService compensationService;

    public CompensationController(final CompensationService compensationService) {
        this.compensationService = compensationService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("compensations", compensationService.findAll());
        return "compensation/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("compensation") final CompensationDTO compensation, Model model) {
        model.addAttribute("emps", employeeRepository.findAll());
        return "compensation/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("compensation") @Valid final CompensationDTO compensationDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes,  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emps", employeeRepository.findAll());
            return "compensation/add";
        }
        compensationService.create(compensationDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("compensation.create.success"));
        return "redirect:/compensations";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        System.out.println(compensationService.get(id).getEmployee().getFirstName());
        System.out.println("------------------------");
        model.addAttribute("compensation", compensationService.get(id));
        model.addAttribute("emps", employeeRepository.findAll());
        return "compensation/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("compensation") @Valid final CompensationDTO compensationDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emps", employeeRepository.findAll());
            return "compensation/edit";
        }
        compensationService.update(id, compensationDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("compensation.update.success"));
        return "redirect:/compensations";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        compensationService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("compensation.delete.success"));
        return "redirect:/compensations";
    }

}
