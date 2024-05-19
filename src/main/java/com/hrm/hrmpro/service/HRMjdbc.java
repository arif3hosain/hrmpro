package com.hrm.hrmpro.service;

import com.hrm.hrmpro.model.UserRegistrationDto;
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


    public void saveUser(UserRegistrationDto user){
        String sql = "INSERT INTO my_user (email, first_name, last_name, password,  organization_id)\n" +
                "VALUES ('"+user.getEmail()+"', '"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPassword()+"', "+user.getOrganization().getId()+");";
        jdbcTemplate.execute(sql);

    }


}
