package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.PerformanceReviewDTO;
import com.hrm.hrmpro.service.PerformanceReviewService;
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
@RequestMapping("/performanceReviews")
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService;

    public PerformanceReviewController(final PerformanceReviewService performanceReviewService) {
        this.performanceReviewService = performanceReviewService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("performanceReviews", performanceReviewService.findAll());
        return "performanceReview/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("performanceReview") final PerformanceReviewDTO performanceReviewDTO) {
        return "performanceReview/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("performanceReview") @Valid final PerformanceReviewDTO performanceReviewDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "performanceReview/add";
        }
        performanceReviewService.create(performanceReviewDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("performanceReview.create.success"));
        return "redirect:/performanceReviews";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("performanceReview", performanceReviewService.get(id));
        return "performanceReview/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("performanceReview") @Valid final PerformanceReviewDTO performanceReviewDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "performanceReview/edit";
        }
        performanceReviewService.update(id, performanceReviewDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("performanceReview.update.success"));
        return "redirect:/performanceReviews";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        performanceReviewService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("performanceReview.delete.success"));
        return "redirect:/performanceReviews";
    }

}
