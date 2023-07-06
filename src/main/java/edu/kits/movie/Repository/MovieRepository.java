package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}