package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/10/2024
 */

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }
    public Employee getEmp(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).getEmployee();
    }
}
