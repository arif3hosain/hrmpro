package com.hrm.hrmpro.service;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/7/2024
 */
import com.hrm.hrmpro.domain.JobApplicant;
import com.hrm.hrmpro.repos.JobApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicantService {

    private final JobApplicantRepo JobApplicantRepo;

    @Autowired
    public JobApplicantService(JobApplicantRepo JobApplicantRepo) {
        this.JobApplicantRepo = JobApplicantRepo;
    }

    public JobApplicant saveJobApplicant(JobApplicant jobApplicant) {
        return JobApplicantRepo.save(jobApplicant);
    }

    public void deleteJobApplicantById(Long id) {
        JobApplicantRepo.deleteById(id);
    }

    public JobApplicant findJobApplicantById(Long id) {
        return JobApplicantRepo.findById(id).orElse(null);
    }

    public List<JobApplicant> findAllJobApplicants() {
        return JobApplicantRepo.findAll();
    }


}
