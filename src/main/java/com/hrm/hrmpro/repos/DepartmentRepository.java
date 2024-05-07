package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByNameIgnoreCase(String name);

}
