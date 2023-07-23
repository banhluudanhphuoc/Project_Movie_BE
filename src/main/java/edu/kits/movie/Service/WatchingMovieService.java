package edu.kits.movie.Service;

import org.springframework.http.ResponseEntity;

public interface WatchingMovieService {
    ResponseEntity<?> watchingMovie(Integer movieId);
}
