package com.furama.service.impl;

import com.furama.model.facility.RentType;
import com.furama.repository.IRentTypeRepository;
import com.furama.service.IRentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentTypeService implements IRentTypeService {
    @Autowired
    private IRentTypeRepository iRentTypeRepository;

    @Override
    public List<RentType> findAll() {
        return this.iRentTypeRepository.findAll();
    }
}
