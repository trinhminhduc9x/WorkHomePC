package moviemanager.service.impl;

import lombok.Setter;
import moviemanager.model.Premiere;
import moviemanager.repository.PremiereRepository;
import moviemanager.service.IPremiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




@Service
public class PremiereService implements IPremiereService {

    @Autowired
    PremiereRepository premiereRepository;


    @Override
    public Page<Premiere> fildPage(Pageable pageable, String numberPremiere, String dayPremiere, String movieId) {
        return premiereRepository.findPageSearchAll(pageable,numberPremiere,dayPremiere,movieId);
    }

    @Override
    public void remove(Integer id) {
        premiereRepository.deleteById(id);
    }

    @Override
    public void save(Premiere premiere) {
        premiereRepository.save(premiere);
    }

    @Override
    public void update(Premiere premiere) {
        premiereRepository.save(premiere);
    }

    @Override
    public Premiere findById(Integer id) {
        return premiereRepository.findById(id).orElse(new Premiere());
    }


}
