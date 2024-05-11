package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface LeaveRepository extends JpaRepository<Leave, Long> {



    @Query("SELECT l FROM Leave  l WHERE l.startDate =:startDate and l.employee.department.id =:id")
    public Leave getLeave(@Param("startDate")LocalDate localDate, @Param("id")Long id);

    List<Leave> findAllByEmployeeId(Long id);
    List<Leave> findAllByEmployeeDepartmentId(Long id);


}
