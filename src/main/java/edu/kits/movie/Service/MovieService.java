package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.MovieDetailResponse;
import edu.kits.movie.Dto.Response.MovieResponse;
import edu.kits.movie.Dto.Response.MovieSeriesResponse;
import edu.kits.movie.Entity.MovieEpisode;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    PaginationResponse<MovieResponse> getMovies(String search, Pageable pageable);
    MovieDetailResponse getMovieDetails(Integer id);
    PaginationResponse<MovieResponse> getTopTenMovieByRating(Pageable pageable);
    PaginationResponse<MovieResponse> getAllMovieSeries(Pageable pageable);
    PaginationResponse<MovieSeriesResponse> getMovieSeriesBySeason(Integer movieId, Integer seasonId, Pageable pageable);
}
