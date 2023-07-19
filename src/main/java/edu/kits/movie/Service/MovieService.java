package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.*;
import edu.kits.movie.Entity.MovieEpisode;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    PaginationResponse<MovieResponse> getMovies(String search, Pageable pageable);
    MovieDetailResponse getMovieDetails(Integer id);
    PaginationResponse<MovieResponse> getTopTenMovieByRating(Pageable pageable);
    PaginationResponse<MovieResponse> getAllMovieSeries(Pageable pageable);
    PaginationResponse<MovieSeriesResponse> getMovieSeriesBySeason(Integer movieId, Integer seasonId, Pageable pageable);

    PaginationResponse<MovieUpcomingResponse> getUpcomingMovies(String search, Pageable pageable);

    MovieUpcomingDetailResponse getMovieUpcomingDetails(Integer id);
    WatchMovieResponse watchMovie(Integer movieId);
}
