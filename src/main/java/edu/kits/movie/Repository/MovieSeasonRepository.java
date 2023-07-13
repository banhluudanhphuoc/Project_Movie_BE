package edu.kits.movie.Repository;

import edu.kits.movie.Domain.MovieSeason;
import edu.kits.movie.Domain.MovieSeasonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSeasonRepository extends JpaRepository<MovieSeason, MovieSeasonId> {

}