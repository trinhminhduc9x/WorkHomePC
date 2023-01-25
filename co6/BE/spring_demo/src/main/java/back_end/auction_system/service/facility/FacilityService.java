package case_study.furama_resort.service.facility;

import case_study.furama_resort.model.facilities.Facility;
import case_study.furama_resort.repository.facility.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService implements IFacilityService {

    @Autowired
    private IFacilityRepository facilitiesRepository;

    @Override
    public void save(Facility facilities) {
        facilitiesRepository.save(facilities);
    }

    @Override
    public Optional<Facility> findById(int id) {
        return facilitiesRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        facilitiesRepository.remove(id);
    }

    @Override
    public Page<Facility> findByNameAndFacilityType(String name, String typeName, Pageable pageable) {
        return facilitiesRepository.findByNameAndFacilityType(name, typeName, pageable);
    }

    @Override
    public List<Facility> findAll() {
        return facilitiesRepository.findAll();
    }

}
