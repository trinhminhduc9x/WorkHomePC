package moviemanager.service;

import moviemanager.model.Premiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPremiereService {
    Page<Premiere> fildPage(Pageable pageable,String numberPremiere,String dayPremiere,String movieId);

    void remove(Integer id);

    void save(Premiere premiere);

    void update(Premiere premiere);

    Premiere findById(Integer id);
}
