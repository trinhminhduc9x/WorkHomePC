package householdmanagement.service;

import householdmanagement.dtoView.HouseholView;
import householdmanagement.repository.HouseholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class HouseholService implements IHouseholService{
    @Autowired
    HouseholRepository householRepository;
    @Override
    public Page<HouseholView> fildPageAll(Pageable pageable) {
        return householRepository.showPage(pageable);
    }
}
