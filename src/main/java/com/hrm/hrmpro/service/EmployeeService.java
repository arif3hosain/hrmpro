package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.model.EmployeeDTO;
import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.repos.DepartmentRepository;
import com.hrm.hrmpro.repos.EmployeeRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserService userService;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> findAll() {
        final List<Employee> employees = employeeRepository.findAll(Sort.by("firstName"));
        return employees.stream()
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .toList();
    }

    public EmployeeDTO get(final Long id) {
        return employeeRepository.findById(id)
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        userService.save(new UserRegistrationDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), "123456"));
        return employeeRepository.save(employee).getId();
    }

    public void update(final Long id, final EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    public void delete(final Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO mapToDTO(final Employee employee, final EmployeeDTO employeeDTO) {
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setJoinDate(employee.getJoinDate());
        return employeeDTO;
    }

    private Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDepartment(departmentRepository.getOne(employeeDTO.getDepartment().getId()));
        employee.setJoinDate(employeeDTO.getJoinDate());
        return employee;
    }

}
