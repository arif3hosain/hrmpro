package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.BenefitDTO;
import com.hrm.hrmpro.service.BenefitService;
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
@RequestMapping("/benefits")
public class BenefitController {

    private final BenefitService benefitService;

    public BenefitController(final BenefitService benefitService) {
        this.benefitService = benefitService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("benefits", benefitService.findAll());
        return "benefit/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("benefit") final BenefitDTO benefitDTO) {
        return "benefit/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("benefit") @Valid final BenefitDTO benefitDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "benefit/add";
        }
        benefitService.create(benefitDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("benefit.create.success"));
        return "redirect:/benefits";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("benefit", benefitService.get(id));
        return "benefit/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("benefit") @Valid final BenefitDTO benefitDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "benefit/edit";
        }
        benefitService.update(id, benefitDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("benefit.update.success"));
        return "redirect:/benefits";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        benefitService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("benefit.delete.success"));
        return "redirect:/benefits";
    }

}
