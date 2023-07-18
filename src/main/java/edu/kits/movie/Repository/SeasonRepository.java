package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
    @Query("select distinct s.season from MovieEpisode s where s.movie.id=:movieId")
    List<Season> findMovieSeason(Integer movieId);
}