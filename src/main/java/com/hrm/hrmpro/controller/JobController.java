package com.hrm.hrmpro.controller;

import com.hrm.hrmpro.domain.Applicant;
import com.hrm.hrmpro.domain.Job;
import com.hrm.hrmpro.domain.JobApplicant;
import com.hrm.hrmpro.model.ApplicantDTO;
import com.hrm.hrmpro.model.JobDTO;
import com.hrm.hrmpro.repos.ApplicantRepository;
import com.hrm.hrmpro.repos.JobApplicantRepo;
import com.hrm.hrmpro.repos.JobRepository;
import com.hrm.hrmpro.service.ApplicantService;
import com.hrm.hrmpro.service.JobApplicantService;
import com.hrm.hrmpro.service.JobService;
import com.hrm.hrmpro.service.SecurityService;
import com.hrm.hrmpro.util.WebUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import java.io.IOException;
import java.io.OutputStream;


@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobApplicantRepo jobApplicantRepo;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private JobApplicantService jobApplicantService;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private SecurityService securityService;

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
    public String getJobDetails(@PathVariable("id") Long id, Model model,final RedirectAttributes redirectAttributes) {
        model.addAttribute("job", jobService.get(id));
        if(securityService.authenticated()){
            if(jobApplicantService.alreadyApplied(id)) {
                redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Already applied to this job"));
                model.addAttribute("applied", true);
                return "job/job-details";
            }
        }
        model.addAttribute("applied", false);
        return "job/job-details";
    }
    @GetMapping("/apply/{id}")
    public String add(@PathVariable("id") Long id, @ModelAttribute("applicant") final JobApplicant applicant, Model model,final RedirectAttributes redirectAttributes) {

        if(!securityService.authenticated()){
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, WebUtils.getMessage("Need login."));
            return "redirect:/login";
        }
        Job job = new Job();
        job.setId(id);
        applicant.setJob(job);
        model.addAttribute("applicant", applicant);
        return "job/apply";
    }

    @PostMapping("/apply")
    public String add(@ModelAttribute("applicant") @Valid final JobApplicant applicant,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if(jobApplicantService.alreadyApplied(applicant.getJob().getId())){
            redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Already applied to this job"));
            return "redirect:/jobs/view/"+applicant.getJob().getId();
        }
        if (bindingResult.hasErrors()) {
            return "job/apply";
        }
        try {
            Applicant app = applicantRepository.getApplicant(securityService.getUser().getEmail());
            Job job = jobRepository.getOne(applicant.getJob().getId());
            JobApplicant application = new JobApplicant(app, job, applicant.getFile().getBytes(), 500);
            jobApplicantRepo.save(application);
        }catch (IOException e){
            redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("file is too large"));
            return "redirect:/jobs/view/"+applicant.getJob().getId();
        }
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Application has been successfully submitted"));
        return "redirect:/jobs/view/"+applicant.getJob().getId();
    }


    @GetMapping("/download-resume/{jobId}/{applicantId}")
    public String  downloadResume(@PathVariable("jobId") Long jobId,@PathVariable("applicantId") Long applicantId,HttpServletResponse response) throws IOException {
        System.out.println(jobId +"----------"+applicantId);
        JobApplicant jobApplicant = jobApplicantRepo.exits(jobId,applicantId);
        System.out.println(jobApplicant.toString());

        if (jobApplicant == null || jobApplicant.getResume() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resume not found");
            return "redirect:/jobs/view/"+jobId;
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"resume.pdf\"");
        response.setContentLength(jobApplicant.getResume().length);

        try (OutputStream out = response.getOutputStream()) {
            out.write(jobApplicant.getResume());
        }
        return "redirect:/jobs/view/"+jobApplicant.getJob().getId();
    }




    @GetMapping("/applicants/{id}")
    public String applicants(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.get(id));
        model.addAttribute("applicants", jobApplicantRepo.getAllByJobId(id));
        return "job/applicants";
    }

    @GetMapping("/hire/{id}/{jobid}")
    public String hire(@PathVariable("id") Long id, @PathVariable("jobid") Long jobid, final RedirectAttributes redirectAttributes) {
        ApplicantDTO applicant = applicantService.get(id);
        applicant.setHired(true);
        applicantService.update(id, applicant);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Applicant hired"));
        return "redirect:/jobs/applicants/"+jobid;
    }


   @GetMapping("/deny/{id}/{jobid}")
    public String deny(@PathVariable("id") Long id, @PathVariable("jobid") Long jobid, final RedirectAttributes redirectAttributes) {
        ApplicantDTO applicant = applicantService.get(id);
        applicant.setDeny(true);
        applicantService.update(id, applicant);
       redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("Application has been denied"));
        return "redirect:/jobs/applicants/"+jobid;
    }



}
