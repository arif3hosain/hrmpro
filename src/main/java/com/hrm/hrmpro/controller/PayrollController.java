package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.PayrollDTO;
import com.hrm.hrmpro.service.PayrollService;
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
@RequestMapping("/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(final PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("payrolls", payrollService.findAll());
        return "payroll/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("payroll") final PayrollDTO payrollDTO) {
        return "payroll/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("payroll") @Valid final PayrollDTO payrollDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "payroll/add";
        }
        payrollService.create(payrollDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("payroll.create.success"));
        return "redirect:/payrolls";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("payroll", payrollService.get(id));
        return "payroll/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("payroll") @Valid final PayrollDTO payrollDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "payroll/edit";
        }
        payrollService.update(id, payrollDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("payroll.update.success"));
        return "redirect:/payrolls";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        payrollService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("payroll.delete.success"));
        return "redirect:/payrolls";
    }

}
