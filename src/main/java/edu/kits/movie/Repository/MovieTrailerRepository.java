package edu.kits.movie.Repository;

import edu.kits.movie.Domain.MovieTrailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTrailerRepository extends JpaRepository<MovieTrailer, Integer> {
}