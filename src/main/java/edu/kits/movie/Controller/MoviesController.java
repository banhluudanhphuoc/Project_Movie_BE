package edu.kits.movie.Controller;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Model.Response.MovieResponse;
import edu.kits.movie.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(Api.UserApi.USER_BASE)
@RequiredArgsConstructor
public class MoviesController {
    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<PaginationResponse<MovieResponse>>
    getMovies(@RequestParam(value = "search", required = false) String search,
              @RequestParam(value = "size") Optional<Integer> size,
              @RequestParam(value = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(movieService.getMovies(search, pageable));
    }
}