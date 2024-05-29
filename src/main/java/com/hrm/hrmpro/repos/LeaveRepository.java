package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Leave;
import com.hrm.hrmpro.util.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface LeaveRepository extends JpaRepository<Leave, Long> {



    @Query("SELECT l FROM Leave  l WHERE l.startDate =:startDate and l.employee.department.id =:id")
    public Leave getLeave(@Param("startDate")LocalDate localDate, @Param("id")Long id);

    long countByStatus(LeaveStatus status);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.status = :status and l.employee.id  =:empId")
    long countByStatus(@Param("status") LeaveStatus status, @Param("empId")Long empId);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.employee.id  =:empId")
    long myLeaveRequ( @Param("empId")Long empId);


    List<Leave> findAllByEmployeeId(Long id);
    List<Leave> findAllByEmployeeDepartmentId(Long id);


}
