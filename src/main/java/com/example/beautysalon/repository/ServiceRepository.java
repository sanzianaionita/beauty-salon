package com.example.beautysalon.repository;

import com.example.beautysalon.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
