package com.hrm.hrmpro.service;



import com.hrm.hrmpro.domain.Role;
import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.repos.RoleRpo;
import com.hrm.hrmpro.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;
   private BCryptPasswordEncoder passwordEncoder;

   @Autowired
   private RoleRpo roleRpo;

   public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
      super();
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public User save(UserRegistrationDto registrationDto) {
      Role role = new Role("");
      if(roleRpo.findAll().isEmpty()){
         role = roleRpo.getRole();
      }


      var user = new User(registrationDto.getFirstName(),
                 registrationDto.getLastName(), 
                  registrationDto.getEmail(),
                   passwordEncoder.encode(registrationDto
                          .getPassword()),
                   Arrays.asList(new Role("ROLE_USER")), registrationDto.getEmployee());
      return userRepository.save(user);
   }

   @Override
   public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {

      var user = userRepository.findByEmail(username);
      if (user == null) {
         throw new UsernameNotFoundException
               ("Invalid username or password.");
      }
      return new org.springframework.security
            .core.userdetails.User(user.getEmail(),
               user.getPassword(),
           mapRolesToAuthorities(user.getRoles()));
   }

   private Collection<? extends GrantedAuthority>
          mapRolesToAuthorities(Collection<Role> roles) {
      
      return roles.stream()
            .map(role -> new SimpleGrantedAuthority
                  (role.getName()))
            .collect(Collectors.toList());
   }

   @Override
   public List<User> getAll() {
      
      return userRepository.findAll();
   }
}
