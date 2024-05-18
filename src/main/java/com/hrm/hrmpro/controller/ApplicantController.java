package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.ApplicantDTO;
import com.hrm.hrmpro.service.ApplicantService;
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
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(final ApplicantService applicantService) {
        this.applicantService = applicantService;
    }


    @GetMapping
    public String list(final Model model) {
        model.addAttribute("applicants", applicantService.findAll());
        return "applicant/list";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute("applicant") final ApplicantDTO applicantDTO) {
        return "applicant/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("applicant") @Valid final ApplicantDTO applicantDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "applicant/add";
        }
        applicantService.create(applicantDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("applicant.create.success"));
        return "redirect:/applicants";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("applicant", applicantService.get(id));
        return "applicant/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("applicant") @Valid final ApplicantDTO applicantDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "applicant/edit";
        }
        applicantService.update(id, applicantDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("applicant.update.success"));
        return "redirect:/applicants";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        applicantService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("applicant.delete.success"));
        return "redirect:/applicants";
    }

}
