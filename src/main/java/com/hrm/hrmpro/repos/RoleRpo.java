package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRpo extends JpaRepository<Role, Long> {


    @Query("SELECT r FROM Role  r WHERE r.name = 'ROLE_USER' ")
    Role getRole();
}
