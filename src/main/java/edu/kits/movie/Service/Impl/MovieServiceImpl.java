package edu.kits.movie.Service.Impl;

import com.google.common.base.Joiner;
import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Common.SearchOperation;
import edu.kits.movie.Dto.Response.*;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Entity.MovieEpisode;
import edu.kits.movie.Repository.MovieRepository;
import edu.kits.movie.Repository.Specification.MovieSpecificationBuilder;
import edu.kits.movie.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MovieServiceImpl implements MovieService {
    private final ModelConverter converter;
    private final MovieRepository movieRepository;

    @Override
    public PaginationResponse<MovieResponse> getMovies(String search, Pageable pageable) {
        Page<Movie> movies;
        MovieSpecificationBuilder builder = new MovieSpecificationBuilder();
        builder.with("premiereDate", "<", LocalDateTime.now(), "", "");
        builder.with("isDeleted", ":", false, "", "");
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Movie> spec = builder.build();
        movies = movieRepository.findAll(spec, pageable);
        List<MovieResponse> movieResponses;
        if (movies.isEmpty()) {
            movieResponses = new ArrayList<>();
            return new PaginationResponse<>(0, 0, 0, movieResponses);
        }
        movieResponses = converter.mapAllByIterator(movies.getContent(), MovieResponse.class);
        return new PaginationResponse<>(movies.getNumber(), movies.getSize(), movies.getTotalPages(), movieResponses);
    }

    @Override
    public MovieDetailResponse getMovieDetails(Integer id) {
        if (id != null) {
            Movie movie = movieRepository.findById(id).orElse(null);
            if (movie != null) {
                return converter.map(movie, MovieDetailResponse.class);
            }
        }
        return null;
    }

    @Override
    public PaginationResponse<MovieResponse> getTopTenMovieByRating(Pageable pageable) {
        Page<Movie> movies = movieRepository.findTopTenMovieByRatings(pageable);
        return new PaginationResponse<>(movies.getNumber(),
                movies.getSize(),
                movies.getTotalPages(),
                converter.mapAllByIterator(movies.getContent(), MovieResponse.class));
    }

    @Override
    public PaginationResponse<MovieResponse> getAllMovieSeries(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAllMoviesSeries(pageable);
        return new PaginationResponse<>(movies.getNumber(),
                movies.getSize(),
                movies.getTotalPages(),
                converter.mapAllByIterator(movies.getContent(), MovieResponse.class));
    }

    @Override
    public PaginationResponse<MovieSeriesResponse> getMovieSeriesBySeason(Integer movieId, Integer seasonId, Pageable pageable) {
        Page<MovieEpisode> movies = movieRepository.findMoviesSeriesBySeason(movieId, seasonId, pageable);
        return new PaginationResponse<>(movies.getNumber(),
                movies.getSize(),
                movies.getTotalPages(),
                converter.mapAllByIterator(movies.getContent(), MovieSeriesResponse.class));
    }

    @Override
    public PaginationResponse<MovieUpcomingResponse> getUpcomingMovies(String search, Pageable pageable) {
        Page<Movie> movies;
        MovieSpecificationBuilder builder = new MovieSpecificationBuilder();
        builder.with("premiereDate", ">:", LocalDateTime.now(), "", "");
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Movie> spec = builder.build();
        movies = movieRepository.findAll(spec, pageable);
        List<MovieUpcomingResponse> movieResponses;
        if (movies.isEmpty()) {
            movieResponses = new ArrayList<>();
            return new PaginationResponse<>(0, 0, 0, movieResponses);
        }
        movieResponses = converter.mapAllByIterator(movies.getContent(), MovieUpcomingResponse.class);
        return new PaginationResponse<>(movies.getNumber(), movies.getSize(), movies.getTotalPages(), movieResponses);
    }

    @Override
    public MovieUpcomingDetailResponse getMovieUpcomingDetails(Integer id) {
        if (id != null) {
            Movie movie = movieRepository.findById(id).orElse(null);
            if (movie != null) {
                return converter.map(movie, MovieUpcomingDetailResponse.class);
            }
        }
        return null;
    }

    @Override
    public WatchMovieResponse watchMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null)
            return null;
        return converter.map(movie, WatchMovieResponse.class);
    }
}
