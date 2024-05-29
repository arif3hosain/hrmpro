package com.hrm.hrmpro.domain;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

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
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    private double expectedSalary;

    @Column(columnDefinition = "boolean default false")
    private boolean hired;

    @Column(columnDefinition = "boolean default false")
    private boolean deny;
    @Lob
    @Column(nullable = true)
    private byte[] resume;

    @Transient
    private MultipartFile file;

    public JobApplicant() {
    }

    public JobApplicant(Applicant applicant, Job job,byte[] resume, double salary ) {
        this.applicant = applicant;
        this.job = job;
        this.resume = resume;
        this.expectedSalary = salary;
        this.setHired(false);
        this.setDeny(false);
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public boolean isDeny() {
        return deny;
    }

    public void setDeny(boolean deny) {
        this.deny = deny;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
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

    @Override
    public String toString() {
        return "JobApplicant{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", job=" + job +
                ", expectedSalary=" + expectedSalary +
                ", hired=" + hired +
                ", deny=" + deny +
                '}';
    }
}
