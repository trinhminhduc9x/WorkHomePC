package com.furama.service.impl;

import com.furama.model.contract.Contract;
import com.furama.repository.IContractRepository;
import com.furama.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository iContractRepository;

    @Override
    public Page<Contract> findByCustomer(String name, Pageable pageable) {
        return this.iContractRepository.findAllByCustomer_NameContaining(name, pageable);
    }

    @Override
    public Contract findById(int id) {
        return this.iContractRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.iContractRepository.deleteById(id);
    }

    @Override
    public void save(Contract contract) {
        this.iContractRepository.save(contract);
    }
}
