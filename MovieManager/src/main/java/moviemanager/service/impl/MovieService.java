package moviemanager.service.impl;

import moviemanager.model.Movie;
import moviemanager.repository.MovieRepository;
import moviemanager.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService implements IMovieService {

    @Autowired
    MovieRepository movieRepository;


    @Override
    public List<Movie> ListMovie() {
        return movieRepository.findAll();
    }
}
