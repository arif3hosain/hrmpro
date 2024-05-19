package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepo extends JpaRepository<Organization, Long> {
}
