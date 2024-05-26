package com.hrm.hrmpro.service;

import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.repos.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by: arif hosain
 * Mail: arif@innoweb.co
 * Created at : 5/19/2024
 */
@Service
public class HRMjdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JobRepository jobRepository;


    public void saveUser(UserRegistrationDto user){
        String sql = "";
        jdbcTemplate.execute(sql);

    }


}
