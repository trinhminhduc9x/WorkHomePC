package case_study.furama_resort.repository.facility;

import case_study.furama_resort.model.facilities.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFacilityTypeRepository extends JpaRepository<FacilityType, Integer> {

    @Query(value = "select * from facility_type where status = 1", nativeQuery = true)
    List<FacilityType> getFacilityTypes();
}
