package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.JobApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobApplicantRepo extends JpaRepository<JobApplicant, Long> {

    @Query("SELECT a FROM JobApplicant  a WHERE a.job.id =:jobId and a.applicant.id =:applicantId")
    JobApplicant exits(@Param("jobId")Long jobId, @Param("applicantId")Long applicantId);
}
