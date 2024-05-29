package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.domain.User;
import com.hrm.hrmpro.model.EmployeeDTO;
import com.hrm.hrmpro.model.UserRegistrationDto;
import com.hrm.hrmpro.repos.*;
import com.hrm.hrmpro.util.Authority;
import com.hrm.hrmpro.util.NotFoundException;

import java.time.LocalDate;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrgRepo orgRepo;
    @Autowired
    private RoleRpo roleRpo;

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

    public void create(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        Employee emp = employeeRepository.save(employee);
        userService.save(new UserRegistrationDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), "123456",emp, employeeDTO.getRole()));
    }

    public void saveEmployee(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        Employee emp = employeeRepository.save(employee);
        userService.save(new UserRegistrationDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), "123456",emp, roleRpo.getByName(Authority.ROLE_EMPLOYEE.toString())));
    }

    public void update(final Long id, final EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
        User user = userRepository.findByEmail(employee.getEmail());
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getFirstName());
        user.setEmail(employee.getEmail());
        userRepository.save(user);
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
        employeeDTO.setDepartmentHead(employee.isDepartmentHead());
        return employeeDTO;
    }

    private Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDepartment(departmentRepository.getOne(employeeDTO.getDepartment().getId()));
        if(employee.getJoinDate() == null){
            employee.setJoinDate(LocalDate.now());
        }
        employee.setDepartmentHead(employeeDTO.isDepartmentHead());
        if(orgRepo.count() != 0){
            employee.setOrganization(orgRepo.findAll().get(0));
        }
        return employee;
    }

}
