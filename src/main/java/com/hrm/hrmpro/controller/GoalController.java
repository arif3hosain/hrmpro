package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.model.GoalDTO;
import com.hrm.hrmpro.service.GoalService;
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
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(final GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("goals", goalService.findAll());
        return "goal/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("goal") final GoalDTO goalDTO) {
        return "goal/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("goal") @Valid final GoalDTO goalDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "goal/add";
        }
        goalService.create(goalDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("goal.create.success"));
        return "redirect:/goals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("goal", goalService.get(id));
        return "goal/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("goal") @Valid final GoalDTO goalDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
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
