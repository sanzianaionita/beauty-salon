package com.example.beautysalon.repository;

import com.example.beautysalon.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByPositionIdIn(List<UUID> positionIds);

    Optional<Employee> findByUserId(UUID userId);

}
