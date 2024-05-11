package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT l FROM Goal  l WHERE  l.employee.department.id =:id")
    public Goal getGoal( @Param("id")Long id);

    List<Goal> findAllByEmployeeId(Long id);
    List<Goal> findAllByEmployeeDepartmentId(Long id);
}
