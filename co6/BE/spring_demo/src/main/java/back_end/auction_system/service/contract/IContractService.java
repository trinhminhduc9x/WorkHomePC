package case_study.furama_resort.service.contract;

import case_study.furama_resort.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContractService {

    void save(Contract contract);

    Optional<Contract> findById(int id);

    void remove(int id);

    Page<Contract> findAll(Pageable pageable);

    List<Contract> findAll();

}
