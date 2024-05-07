package com.hrm.hrmpro.domain;

import jakarta.persistence.*;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/7/2024
 */


@Entity
public class JobApplicant {


    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false, referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false, referencedColumnName = "id")
    private Job job;

    public JobApplicant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
