package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GoalRepository extends JpaRepository<Goal, Long> {
}
