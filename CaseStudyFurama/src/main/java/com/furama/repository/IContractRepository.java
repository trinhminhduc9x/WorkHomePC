package com.furama.repository;

import com.furama.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    Page<Contract> findAllByCustomer_NameContaining(String name, Pageable pageable);
}
