package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.JobApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicantRepo extends JpaRepository<JobApplicant, Long> {
}
