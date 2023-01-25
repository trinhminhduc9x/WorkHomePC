package case_study.furama_resort.service.facility;

import case_study.furama_resort.model.facilities.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IFacilityService {

    void save(Facility facility);

    Optional<Facility> findById(int id);

    void remove(int id);

    Page<Facility> findByNameAndFacilityType(String name,
                                             String typeName,
                                             Pageable pageable);

    List<Facility> findAll();

}
