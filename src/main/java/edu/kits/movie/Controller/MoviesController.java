package edu.kits.movie.Controller;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.*;
import edu.kits.movie.Service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping(Api.UserApi.USER_BASE)
@RequiredArgsConstructor
@Validated
public class MoviesController {
    private final MovieService movieService;

    @Operation(summary = "Get All products or search product",
            description = "example for search search=movieName:value,genres~value" +
                    " operator(>,<,:,~) : mean equal, ~ mean like")
    @GetMapping("/movies")
    public ResponseEntity<PaginationResponse<MovieResponse>>
    getMovies(@RequestParam(value = "search", required = false) String search,
              @RequestParam(value = "size") Optional<Integer> size,
              @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getMovies(search, pageable));
    }

    @GetMapping("/movies/details/{movieId}")
    public ResponseEntity<MovieDetailResponse> getMovieDetails(@PathVariable Optional<Integer> movieId) {
        return movieId.map(id -> ResponseEntity.ok(movieService.getMovieDetails(id))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/movies/top-ten")
    public ResponseEntity<PaginationResponse<MovieResponse>>
    getMovieDetails(@RequestParam(value = "size") Optional<Integer> size,
                    @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getTopTenMovieByRating(pageable));
    }

    @GetMapping("/movies/series")
    public ResponseEntity<PaginationResponse<MovieResponse>>
    getAllMovieSeries(@RequestParam(value = "size") Optional<Integer> size,
                      @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getAllMovieSeries(pageable));
    }

    @GetMapping("/movies-episode/{movieId}/season/{seasonId}")
    public ResponseEntity<PaginationResponse<MovieSeriesResponse>>
    getMovieSeriesBySeason(@RequestParam(value = "size") Optional<Integer> size,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @PathVariable @Valid @NotNull Integer movieId,
                           @PathVariable @Valid @NotNull Integer seasonId) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getMovieSeriesBySeason(movieId, seasonId, pageable));
    }

    @GetMapping("/movies/upcoming")
    public ResponseEntity<PaginationResponse<MovieUpcomingResponse>>
    getUpcomingMovies(@RequestParam(value = "search", required = false) String search,
                      @RequestParam(value = "size") Optional<Integer> size,
                      @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getUpcomingMovies(search, pageable));
    }

    @GetMapping("/movies/upcoming/detail/{movieId}")
    public ResponseEntity<MovieUpcomingDetailResponse> getUpcomingMovieDetails(@PathVariable Optional<Integer> movieId) {
        return movieId.map(id -> ResponseEntity.ok(movieService.getMovieUpcomingDetails(id))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @GetMapping("/movies/watching/{movieId}")
//    public ResponseEntity<?> watchMovie(@PathVariable Integer movieId){
//        WatchMovieResponse watchMovieResponse = movieService.watchMovie(movieId);
//        if (watchMovieResponse == null)
//            return ResponseEntity.badRequest().body("Movie Not found!");
//        return ResponseEntity.ok(watchMovieResponse);
//    }
}
