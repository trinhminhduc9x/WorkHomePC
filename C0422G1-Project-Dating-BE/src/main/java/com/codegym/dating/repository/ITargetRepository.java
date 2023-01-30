package com.codegym.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITargetRepository extends JpaRepository<Target, Integer> {

    @Query(value = "select * from target", nativeQuery = true)
    List<Target> findAllTarget();
}