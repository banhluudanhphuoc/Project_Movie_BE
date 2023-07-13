package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
}