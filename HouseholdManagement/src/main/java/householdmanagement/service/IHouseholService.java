package householdmanagement.service;

import householdmanagement.dtoView.HouseholView;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface IHouseholService {
    Page<HouseholView> fildPageAll(Pageable pageable);
}
