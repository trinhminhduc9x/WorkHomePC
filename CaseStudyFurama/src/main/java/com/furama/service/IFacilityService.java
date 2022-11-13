package com.furama.service;

import com.furama.model.facility.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFacilityService {
    void save(Facility facility);
    Page<Facility> findAll(Pageable pageable);
    Facility findById(int id);
    void deleteById(int id);
    Page<Facility> findAllByFacilityType(Integer id, Pageable pageable);
    List<Facility> findAllList();
}
