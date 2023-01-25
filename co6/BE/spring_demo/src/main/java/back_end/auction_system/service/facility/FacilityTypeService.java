package case_study.furama_resort.service.facility;

import case_study.furama_resort.model.facilities.FacilityType;
import case_study.furama_resort.repository.facility.IFacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeService implements IFacilityTypeService {

    @Autowired
    private IFacilityTypeRepository facilityTypeRepository;

    @Override
    public List<FacilityType> getFacilityTypes() {
        return facilityTypeRepository.getFacilityTypes();
    }
}
