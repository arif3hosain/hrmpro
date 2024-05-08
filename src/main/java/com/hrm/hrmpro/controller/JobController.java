package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.domain.Job;
import com.hrm.hrmpro.model.ApplicantDTO;
import com.hrm.hrmpro.model.JobDTO;
import com.hrm.hrmpro.repos.JobRepository;
import com.hrm.hrmpro.service.JobService;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    @Autowired
    private JobRepository jobRepository;

    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "job/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("job") final JobDTO jobDTO) {
        return "job/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("job") @Valid final JobDTO jobDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "job/add";
        }
        jobService.create(jobDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("job.create.success"));
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("job", jobService.get(id));
        return "job/edit";
    }

    @GetMapping("/detail/{id}")
    public String edits(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("job", jobService.get(id));
        return "job/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("job") @Valid final JobDTO jobDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "job/edit";
        }
        jobService.update(id, jobDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("job.update.success"));
        return "redirect:/jobs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        jobService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("job.delete.success"));
        return "redirect:/jobs";
    }


    @GetMapping("/view/{id}")
    public String getJobDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.get(id));
        return "job/job-details";
    }

/*
    @GetMapping("/apply/{id}")
    public String apply(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.get(id));
        return "job/job-details";
    }

*/

    @GetMapping("/apply/{id}")
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

}
