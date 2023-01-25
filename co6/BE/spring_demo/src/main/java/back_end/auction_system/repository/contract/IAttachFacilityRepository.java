package case_study.furama_resort.repository.contract;

import case_study.furama_resort.model.contract.AttachFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAttachFacilityRepository extends JpaRepository<AttachFacility, Integer> {

    @Query(value = "select * from `attach_facility` where status= 1", nativeQuery = true)
    List<AttachFacility> findAll();
}