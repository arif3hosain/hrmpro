package com.hrm.hrmpro.model;

import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.domain.Organization;

import java.beans.Transient;

public class UserRegistrationDto {

   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private Employee employee;
   private Organization organization;
   private Long registerTypeId;


   public UserRegistrationDto() {

   }

   public UserRegistrationDto(String firstName, String lastName, String email, String password, Employee employee) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.employee = employee;
   }

   public Long getRegisterTypeId() {
      return registerTypeId;
   }

   public void setRegisterTypeId(Long registerTypeId) {
      this.registerTypeId = registerTypeId;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Employee getEmployee() {
      return employee;
   }

   public void setEmployee(Employee employee) {
      this.employee = employee;
   }

   public Organization getOrganization() {
      return organization;
   }

   public void setOrganization(Organization organization) {
      this.organization = organization;
   }

   @Override
   public String toString() {
      return "UserRegistrationDto{" +
              "firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", employee=" + employee +
              ", registerTypeId=" + registerTypeId +
              '}';
   }
}