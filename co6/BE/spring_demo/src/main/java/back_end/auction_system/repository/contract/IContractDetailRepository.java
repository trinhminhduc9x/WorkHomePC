package case_study.furama_resort.repository.contract;

import case_study.furama_resort.model.contract.ContractDetail;
import case_study.furama_resort.model.dto.contract.ContractDetailAttachFacilityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IContractDetailRepository extends JpaRepository<ContractDetail, Integer> {

    @Query(value = "select * from `contract_detail` where status= 1", nativeQuery = true)
    List<ContractDetail> findAll();

    @Transactional
    @Modifying
    @Query(value = "update `contract_detail` set status = 0 where id = :id", nativeQuery = true)
    void remove(@Param("id") int id);

    @Query(value = "select af.name as name, af.cost as cost, af.unit as unit, sum(cd.quantity) as totalQuantity " +
            "from contract_detail cd inner join attach_facility af " +
            "on cd.attach_facility_id = af.id " +
            "where cd.contract_id = :id and cd.status = 1 " +
            "group by cd.attach_facility_id ", nativeQuery = true)
    List<ContractDetailAttachFacilityDto> findByContractId(@Param("id") int id);

    @Query(value =
            "select af.name as `name`, af.cost as cost, af.unit as unit, sum(cd.quantity) as totalQuantity " +
            "from contract_detail cd inner join attach_facility af " +
            "on cd.attach_facility_id = af.id " +
            "inner join contract ct on cd.contract_id = ct.id " +
            "where ct.customer_id = :id and cd.status = 1 " +
            "group by cd.attach_facility_id ", nativeQuery = true)
    List<ContractDetailAttachFacilityDto> findByCustomer(@Param("id") int id);
}
