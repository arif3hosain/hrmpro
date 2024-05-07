package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PayrollRepository extends JpaRepository<Payroll, Long> {
}
