package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.GoalDTO;
import com.hrm.hrmpro.repos.EmployeeRepository;
import com.hrm.hrmpro.repos.GoalRepository;
import com.hrm.hrmpro.service.GoalService;
import com.hrm.hrmpro.service.SecurityService;
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
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private SecurityService securityService;
    private final GoalService goalService;

    public GoalController(final GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String list(final Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isHR = false;
        if (auth != null) {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if ("ROLE_HR".equals(authority.getAuthority())) {
                    model.addAttribute("goals", goalService.findAll());
                    isHR = true;
                }
            }
        }
       if(!isHR){
           if(securityService.getEmp().isDepartmentHead() ){
               model.addAttribute("goals", goalRepository.findAllByEmployeeDepartmentId(securityService.getEmp().getDepartment().getId()));
           }else {
               model.addAttribute("goals", goalRepository.findAllByEmployeeId(securityService.getEmp().getId()));
           }
       }
        return "goal/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("goal") final GoalDTO goalDTO, Model model) {
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

        return "goal/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("goal") @Valid final GoalDTO goalDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emps", employeeRepository.findAll());
            return "goal/add";
        }
        goalService.create(goalDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("goal.create.success"));
        return "redirect:/goals";
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
        model.addAttribute("goal", goalService.get(id));
        return "goal/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("goal") @Valid final GoalDTO goalDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emps", employeeRepository.findAll());
            return "goal/edit";
        }
        goalService.update(id, goalDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("goal.update.success"));
        return "redirect:/goals";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        goalService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("goal.delete.success"));
        return "redirect:/goals";
    }

}
