package case_study.furama_resort.repository.contract;

import case_study.furama_resort.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "select * from `contract` where status= 1", nativeQuery = true)
    Page<Contract> findAll(Pageable pageable);

    @Query(value = "select * from `contract` where status= 1", nativeQuery = true)
    List<Contract> findAll();

    @Query(value = "select * from `contract` where id=:id and status = 1", nativeQuery = true)
    Optional<Contract> findById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update customer set status = 0 where id = :id", nativeQuery = true)
    void remove(@Param("id") int id);
}
