package com.example.beautysalon.utils;

import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.enums.Authority;
import com.example.beautysalon.repository.BeautyUserRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.security.config.ConfigClass;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    private final BeautyUserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final ConfigClass configClass;

    public DataLoader(BeautyUserRepository userRepository, EmployeeRepository employeeRepository, ConfigClass configClass) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.configClass = configClass;
    }

    public void run(ApplicationArguments args) {

        if (userRepository.findAllByAuthority(Authority.ADMIN).isEmpty()) {
            BeautyUser admin = new BeautyUser();
            admin.setEmail("admin@admin.com");
            admin.setAuthority(Authority.ADMIN);
            admin.setId(UUID.randomUUID());
            admin.setPassword(configClass.passwordEncoder().encode("admin"));
            BeautyUser savedAdmin = userRepository.save(admin);

            Employee employee = new Employee();
            employee.setId(UUID.randomUUID());
            employee.setUser(savedAdmin);
            employee.setWage(999);
            employee.setEmploymentDate(LocalDate.now());
            employee.setFirstName("admin");
            employee.setLastName("admin");

            employeeRepository.save(employee);
        }
    }
}
