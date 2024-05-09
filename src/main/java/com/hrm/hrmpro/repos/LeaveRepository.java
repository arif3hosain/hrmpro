package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
