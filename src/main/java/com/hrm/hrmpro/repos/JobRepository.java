package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long> {
}
