package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Job;
import com.hrm.hrmpro.model.JobDTO;
import com.hrm.hrmpro.repos.JobRepository;
import com.hrm.hrmpro.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> findAll() {
        final List<Job> jobs = jobRepository.findAll(Sort.by("id"));
        return jobs.stream()
                .map(job -> mapToDTO(job, new JobDTO()))
                .toList();
    }

    public JobDTO get(final Long id) {
        return jobRepository.findById(id)
                .map(job -> mapToDTO(job, new JobDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final JobDTO jobDTO) {
        final Job job = new Job();
        mapToEntity(jobDTO, job);
        job.setPostedDate(LocalDate.now());
        return jobRepository.save(job).getId();
    }

    public void update(final Long id, final JobDTO jobDTO) {
        final Job job = jobRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(jobDTO, job);
        jobRepository.save(job);
    }

    public void delete(final Long id) {
        jobRepository.deleteById(id);
    }

    private JobDTO mapToDTO(final Job job, final JobDTO jobDTO) {
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setCategory(job.getCategory());
        jobDTO.setSalary(job.getSalary());
        jobDTO.setPostedDate(LocalDate.now());
        jobDTO.setActive(job.isActive());
        return jobDTO;
    }

    private Job mapToEntity(final JobDTO jobDTO, final Job job) {
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setCategory(jobDTO.getCategory());
        job.setSalary(jobDTO.getSalary());
        job.setActive(jobDTO.isActive());
        return job;
    }

}
