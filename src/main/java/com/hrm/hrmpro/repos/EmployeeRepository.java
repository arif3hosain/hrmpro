package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDepartmentId(Long id);
}
