package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.PerformanceReviewDTO;
import com.hrm.hrmpro.repos.EmployeeRepository;
import com.hrm.hrmpro.repos.GoalRepository;
import com.hrm.hrmpro.service.PerformanceReviewService;
import com.hrm.hrmpro.service.SecurityService;
import com.hrm.hrmpro.util.Performance;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SecurityService securityService;
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
            @ModelAttribute("performanceReview") final PerformanceReviewDTO performanceReviewDTO, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isHR = false;
        if (auth != null) {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if ("ROLE_HR".equals(authority.getAuthority())) {
                    model.addAttribute("emps", employeeRepository.findAll());
                    isHR = true;
                }
            }
        }
        if(!isHR){
            if(securityService.getEmp().isDepartmentHead() ){
                model.addAttribute("emps", employeeRepository.findAllByDepartmentId(securityService.getEmp().getDepartment().getId()));
            }
        }
        model.addAttribute("performance", Performance.values());
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isHR = false;
        if (auth != null) {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if ("ROLE_HR".equals(authority.getAuthority())) {
                    model.addAttribute("emps", employeeRepository.findAll());
                    isHR = true;
                }
            }
        }
        if(!isHR){
            if(securityService.getEmp().isDepartmentHead() ){
                model.addAttribute("emps", employeeRepository.findAllByDepartmentId(securityService.getEmp().getDepartment().getId()));
            }
        }
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
