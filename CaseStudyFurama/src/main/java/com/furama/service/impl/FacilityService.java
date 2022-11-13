package com.furama.service.impl;

import com.furama.model.facility.Facility;
import com.furama.repository.IFacilityRepository;
import com.furama.service.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService implements IFacilityService {
    @Autowired
    private IFacilityRepository iFacilityRepository;
    @Override
    public void save(Facility facility) {
        this.iFacilityRepository.save(facility);
    }

    @Override
    public Page<Facility> findAll(Pageable pageable) {
        return this.iFacilityRepository.findAll(pageable);
    }

    @Override
    public Facility findById(int id) {
        return this.iFacilityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.iFacilityRepository.deleteById(id);
    }

    @Override
    public Page<Facility> findAllByFacilityType(Integer id, Pageable pageable) {
        return this.iFacilityRepository.findAllByFacilityType_Id(id, pageable);
    }

    @Override
    public List<Facility> findAllList() {
        return this.iFacilityRepository.findAll();
    }
}
