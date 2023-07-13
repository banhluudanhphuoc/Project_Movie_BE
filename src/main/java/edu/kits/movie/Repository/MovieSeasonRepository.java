package edu.kits.movie.Repository;

import edu.kits.movie.Entity.MovieSeason;
import edu.kits.movie.Entity.MovieSeasonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSeasonRepository extends JpaRepository<MovieSeason, MovieSeasonId> {

}