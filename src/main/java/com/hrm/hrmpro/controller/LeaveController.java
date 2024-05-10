package com.hrm.hrmpro.controller;


import com.hrm.hrmpro.model.LeaveDTO;
import com.hrm.hrmpro.service.LeaveService;
import com.hrm.hrmpro.util.LeaveStatus;
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
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(final LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("leaves", leaveService.findAll());
        return "leave/list";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute("leave") final LeaveDTO leaveDTO) {
        return "leave/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("leave") @Valid final LeaveDTO leaveDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "leave/add";
        }
        leaveService.create(leaveDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("leave.create.success"));
        return "redirect:/leaves";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("leave", leaveService.get(id));
        return "leave/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
                       @ModelAttribute("leave") @Valid final LeaveDTO leaveDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "leave/edit";
        }
        leaveService.update(id, leaveDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("leave.update.success"));
        return "redirect:/leaves";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
                         final RedirectAttributes redirectAttributes) {
        leaveService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("leave.delete.success"));
        return "redirect:/leaves";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable(name = "id") final Long id,
                         final RedirectAttributes redirectAttributes) {
        LeaveDTO dto =  leaveService.get(id);
        dto.setStatus(LeaveStatus.APPROVED);
        leaveService.update(id, dto );
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("Leave approved."));
        return "redirect:/leaves";
    }

    @PostMapping("/reject/{id}")
    public String cancel(@PathVariable(name = "id") final Long id,
                         final RedirectAttributes redirectAttributes) {
        LeaveDTO dto =  leaveService.get(id);
        dto.setStatus(LeaveStatus.REJECTED);
        leaveService.update(id, dto );
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("Leave rejected."));
        return "redirect:/leaves";
    }

}
