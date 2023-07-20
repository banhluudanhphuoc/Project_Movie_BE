package edu.kits.movie.Repository;

import edu.kits.movie.Entity.MovieEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEpisodeRepository extends JpaRepository<MovieEpisode,Integer> {
}
