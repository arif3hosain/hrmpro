package com.hrm.hrmpro.repos;


import com.hrm.hrmpro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   
   User findByEmail(String email);
}