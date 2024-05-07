package com.hrm.hrmpro.repos;

import com.hrm.hrmpro.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

}
