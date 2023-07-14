package edu.kits.movie.Service.Admin;

import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieAdminService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieAdminService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
