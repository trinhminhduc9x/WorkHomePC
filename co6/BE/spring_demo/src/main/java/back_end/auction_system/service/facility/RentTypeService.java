package case_study.furama_resort.service.facility;

import case_study.furama_resort.model.facilities.RentType;
import case_study.furama_resort.repository.facility.IRentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentTypeService implements IRentTypeService {

    @Autowired
    private IRentTypeRepository rentTypeRepository;

    @Override
    public List<RentType> getRentTypes() {
        return rentTypeRepository.getRentTypes();
    }
}
