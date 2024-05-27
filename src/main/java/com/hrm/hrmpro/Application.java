package com.hrm.hrmpro;

import com.hrm.hrmpro.domain.Department;
import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.domain.Organization;
import com.hrm.hrmpro.domain.Role;
import com.hrm.hrmpro.model.EmployeeDTO;
import com.hrm.hrmpro.repos.DepartmentRepository;
import com.hrm.hrmpro.repos.EmployeeRepository;
import com.hrm.hrmpro.repos.OrgRepo;
import com.hrm.hrmpro.repos.RoleRpo;
import com.hrm.hrmpro.service.EmployeeService;
import com.hrm.hrmpro.util.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application  implements ApplicationRunner {

    @Autowired
    private RoleRpo roleRpo;
    @Autowired
    private OrgRepo orgRepo;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeService employeeService;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("===================================");
        System.out.println("            App Started            ");
        System.out.println("===================================");
    }

    @Override
    public void run(ApplicationArguments args)  {
        if(roleRpo.count() == 0){
            roleRpo.save(new Role(1L,"ROLE_ADMIN"));
            roleRpo.save(new Role(2L,"ROLE_HR"));
            roleRpo.save(new Role(3L,"ROLE_EMPLOYEE"));
            roleRpo.save(new Role(4L,"ROLE_APPLICANT"));
            System.out.println("Role created");
        }
        if(orgRepo.count() == 0){
            orgRepo.save(new Organization(1L,"Global HR Limited"));
            System.out.println("Organization created");
        }
        Department dept = null;
        if (departmentRepository.count() == 0) {
            dept = departmentRepository.save(new Department("Human Resource Management"));
        }
       if(employeeRepository.count() == 0){
            employeeService.create(new EmployeeDTO("Admin", "Admin", "admin@gmail.com", true, dept == null ? departmentRepository.findAll().get(0): null, roleRpo.getByName(Authority.ROLE_ADMIN.toString())));
            employeeService.create(new EmployeeDTO("HR", "Admin", "hr@gmail.com", true, dept == null ? departmentRepository.findAll().get(0): null, roleRpo.getByName(Authority.ROLE_HR.toString())));
        }


    }
}
