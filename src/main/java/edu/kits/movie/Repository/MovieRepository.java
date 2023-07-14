package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Entity.MovieEpisode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {
    @Query("select m from Movie m order by m.averageRatingPoint desc ")
    Page<Movie> findTopTenMovieByRatings(Pageable pageable);

    @Query("select e from MovieEpisode e where e.movie.id=:movieId " +
            "and e.season.id=:seasonId ")
    Page<MovieEpisode> findMoviesSeriesBySeason(Integer movieId, Integer seasonId, Pageable pageable);

    @Query("select m.movie from MovieGenre m where m.genres.id=14")
    Page<Movie> findAllMoviesSeries(Pageable pageable);
}