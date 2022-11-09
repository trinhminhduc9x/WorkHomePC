package casestudy.repository.contract;

import casestudy.dto.DucDepTrai;
import casestudy.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractDetailRepository extends JpaRepository<ContractDetail,Integer> {
    @Query(value = " select * from contract_detail where contract_id = :contractId ", nativeQuery = true)
    List<ContractDetail> findListById(@Param("contractId") Integer contractId);

    @Query(value = " SELECT \n" +
            "    attach_facility.name nameFacility,\n" +
            "    customer.name nameCustomer\n" +
            "FROM\n" +
            "    furama_minhduc.contract_detail\n" +
            "        join\n" +
            "    attach_facility \n" +
            "    on attach_facility.id=contract_detail.attach_facility_id\n" +
            "    join\n" +
            "    contract \n" +
            "    on contract.id=contract_detail.contract_id\n" +
            "    join\n" +
            "    customer\n" +
            "    on customer.id=contract.customer_id\n" +
            "WHERE\n" +
            "    customer_id = :customerId ", nativeQuery = true)
    List<DucDepTrai> findListByCustomerId(@Param("customerId") Integer contractId);
}
