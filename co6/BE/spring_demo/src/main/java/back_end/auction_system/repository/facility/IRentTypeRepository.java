package case_study.furama_resort.repository.facility;

import case_study.furama_resort.model.facilities.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRentTypeRepository extends JpaRepository<RentType, Integer> {

    @Query(value = "select * from rent_type where status = 1", nativeQuery = true)
    List<RentType> getRentTypes();
}
