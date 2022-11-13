package com.furama.service.impl;

import com.furama.model.facility.FacilityType;
import com.furama.repository.IFacilityTypeRepository;
import com.furama.service.IFacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeService implements IFacilityTypeService {
    @Autowired
    private IFacilityTypeRepository iFacilityTypeRepository;

    @Override
    public List<FacilityType> findAll() {
        return this.iFacilityTypeRepository.findAll();
    }
}
