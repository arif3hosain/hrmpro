package com.hrm.hrmpro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {

      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       String [] defaultPath = {"/jobs", "/jobs/view/**", "/jobs/apply/**"};
       http
               .authorizeHttpRequests((requests) -> requests
                       .requestMatchers(defaultPath).permitAll()
                       .requestMatchers(defaultPath).permitAll()
//                       .requestMatchers("/compensations/add").hasAnyRole("ROLE_HR")
                       .requestMatchers("/webjars/bootstrap/**", "/webjars/flatpickr/**", "/css/**", "/js/**").permitAll()
                       .requestMatchers(
                               "/registration/**",
                               "/js/**",
                               "/css/**",
                               "/images/**").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin((form) -> form
                       .loginPage("/login")
                       .permitAll()
               )
               .exceptionHandling((exceptionHandling) -> exceptionHandling
                       .accessDeniedPage("/access-denied")
               )
               .logout((logout) -> logout
                       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                       .logoutSuccessUrl("/login?logout")
                       .invalidateHttpSession(true)
                       .clearAuthentication(true)
                       .permitAll()
               );





      return http.build();
   }
}