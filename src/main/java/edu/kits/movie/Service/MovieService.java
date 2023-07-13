package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.MovieDetailResponse;
import edu.kits.movie.Dto.Response.MovieResponse;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    PaginationResponse<MovieResponse> getMovies(String search, Pageable pageable);
    MovieDetailResponse getMovieDetails(Integer id);
    PaginationResponse<MovieResponse> getTopTenMovieByRating(Pageable pageable);
}
