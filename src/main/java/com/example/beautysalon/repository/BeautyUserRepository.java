package com.example.beautysalon.repository;

import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.model.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeautyUserRepository extends JpaRepository<BeautyUser, UUID> {
    Optional<BeautyUser> findByEmail(String email);

    List<BeautyUser> findAllByAuthority(Authority authority);
}
