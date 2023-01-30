package com.codegym.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStatusActiveRepository extends JpaRepository<StatusActive, Integer> {
    Optional<StatusActive> findById(Integer id);
}
