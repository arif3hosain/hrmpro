package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.model.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
   
   void save(UserRegistrationDto registrationDto);
   List<User> getAll();
}