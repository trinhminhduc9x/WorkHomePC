package casestudy.repository.contract;

import casestudy.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractDetailRepository extends JpaRepository<ContractDetail,Integer> {
    @Query(value = " select * from contract_detail where contract_id = :contractId ", nativeQuery = true)
    List<ContractDetail> findListById(@Param("contractId") Integer contractId);

    @Query(value = " SELECT \n" +
            "    *\n" +
            "FROM\n" +
            "    furama_minhduc.contract_detail\n" +
            "    join\n" +
            "    contract \n" +
            "    on contract.id=contract_detail.contract_id\n" +
            "    join\n" +
            "    attach_facility\n" +
            "    on attach_facility.id=contract_detail.attach_facility_id where contract_id = :contractId ", nativeQuery = true)
    List<Object> findListByIdAll(@Param("contractId") Integer contractId);
}
