package edu.kits.movie.Repository;

import edu.kits.movie.Entity.MovieGenre;
import edu.kits.movie.Entity.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {
}