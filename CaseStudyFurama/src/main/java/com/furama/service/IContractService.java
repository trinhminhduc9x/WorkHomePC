package com.furama.service;

import com.furama.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {
    Page<Contract> findByCustomer(String name, Pageable pageable);

    Contract findById(int id);

    void deleteById(int id);

    void save(Contract contract);
}
