package com.hrm.hrmpro.service;


import com.hrm.hrmpro.domain.Applicant;
import com.hrm.hrmpro.domain.Role;
import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.model.EmployeeDTO;
import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.repos.ApplicantRepository;
import com.hrm.hrmpro.repos.OrgRepo;
import com.hrm.hrmpro.repos.RoleRpo;
import com.hrm.hrmpro.repos.UserRepository;
import com.hrm.hrmpro.util.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;
   private BCryptPasswordEncoder passwordEncoder;
   @Autowired
   private RoleRpo roleRpo;
   @Autowired
   private OrgRepo orgRepo;
   @Autowired
   private ApplicantRepository applicantRepository;

   public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
      super();
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Transactional
   @Override
   public void save(UserRegistrationDto registrationDto) {
      Role authority = roleRpo.getByName(registrationDto.getRole().getName());
      List<Role> authorities = new ArrayList<>();
      authorities.add(authority);
      User user = new User();
      user.setFirstName(registrationDto.getFirstName());
      user.setLastName(registrationDto.getLastName());
      user.setEmail(registrationDto.getEmail());
      user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
      user.setRoles(authorities);
      if(authority.getName().equalsIgnoreCase(Authority.ROLE_APPLICANT.toString())){
         Applicant applicant = new Applicant(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail());
         applicantRepository.save(applicant);
      }else {
         user.setEmployee(registrationDto.getEmployee());
      }
      User u = userRepository.save(user);


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
}
