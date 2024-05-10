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
       http
               .authorizeHttpRequests((requests) -> requests
                       .requestMatchers("/jobs").hasRole("ADMIN")
                       .requestMatchers(
                               "/registration/**",
                               "/js/**",
                               "/css/**",
                               "/img/**").permitAll()
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