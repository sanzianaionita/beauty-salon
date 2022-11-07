package com.example.beautysalon.repository;

import com.example.beautysalon.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SalonRepository extends JpaRepository<Salon, UUID> {

    List<Salon> findAllByLocationId(UUID locationId);

    List<Salon> findAllBySalonName(String salonName);
}
