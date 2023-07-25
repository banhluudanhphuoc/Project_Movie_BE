package edu.kits.movie.Controller.Admin;


import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Request.CreateMovieEpisodeRequest;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Request.UpdateMovieRequest;
import edu.kits.movie.Dto.Response.*;
import edu.kits.movie.Service.Admin.MovieAdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Api.AdminApi.ADMIN_BASE)
@RequiredArgsConstructor
@Slf4j
public class MovieAdminController {
    private final MovieAdminService movieAdminService;

    @PostMapping("movies")
    public ResponseEntity<CreateMovieResponse>
    createMovie(@RequestPart("request") CreateMovieRequest request,
                @RequestPart("video") String video,
                @RequestPart("mainPoster") MultipartFile mainPoster,
                @RequestPart("posters") List<MultipartFile> posters,
                @RequestPart("trailers") List<String> trailers,
                @RequestPart("banner") MultipartFile banner
    ) {
        return ResponseEntity.ok(movieAdminService.createMovie(request, video, mainPoster, posters, trailers,banner));
    }

    @PostMapping("movies-episode")
    public ResponseEntity<CreateMovieEpisodeResponse>
    createMovieEpisode(@RequestBody @Valid CreateMovieEpisodeRequest request) {
        return ResponseEntity.ok(movieAdminService.createMovieEpisode(request));
    }

    @PutMapping("movies")
    public ResponseEntity<?>
    updateMovie(
            @RequestPart("movie") UpdateMovieRequest request,
            @RequestPart(name = "mainPoster", required = false) MultipartFile mainPoster) {
        try {
            UpdateMovieResponse movieResponse = movieAdminService.updateMovie(request, mainPoster);
            if (movieResponse != null)
                return ResponseEntity.ok(movieResponse);
            return ResponseEntity.badRequest().body("Movie request cannot be null");
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body("Cannot upload file!! ");
        }
    }

    @DeleteMapping("movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer movieId) {
        movieAdminService.deleteMovie(movieId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get All products or search product",
            description = "example for search search=movieName:value,genres~value" +
                    " operator(>,<,:,~) : mean equal, ~ mean like")
    @GetMapping("/movies")
    public ResponseEntity<PaginationResponse<MovieResponse>>
    getMovies(@RequestParam(value = "search", required = false) String search,
              @RequestParam(value = "size") Optional<Integer> size,
              @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieAdminService.getAllMovies(search, pageable));
    }

    @GetMapping("/movies/details/{movieId}")
    public ResponseEntity<MovieDetailResponse> getMovieDetails(@PathVariable Optional<Integer> movieId) {
        return movieId.map(id -> ResponseEntity.ok(movieAdminService.getMovieDetails(id))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
