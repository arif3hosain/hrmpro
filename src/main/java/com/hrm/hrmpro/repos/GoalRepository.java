package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Goal;
import com.hrm.hrmpro.util.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT l FROM Goal  l WHERE  l.employee.department.id =:id")
    public Goal getGoal( @Param("id")Long id);

    @Query("SELECT COUNT(l) FROM Goal l WHERE l.completed = true and l.employee.id  =:empId")
    long countByCompletedTrue(@Param("empId")Long empId);

    @Query("SELECT COUNT(l) FROM Goal l WHERE l.completed = false and l.employee.id  =:empId")
    long countByCompletedFalse(@Param("empId")Long empId);

    List<Goal> findAllByEmployeeId(Long empId);

    List<Goal> findAllByEmployeeDepartmentId(Long id);
}
