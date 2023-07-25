package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Service.WatchingMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
@RestController
public class WatchingMovieController {
    private final WatchingMovieService watchingMovieService;

    @GetMapping("watching-movie/{movieId}")
    public ResponseEntity<?> watchMovie(@PathVariable Integer movieId){
        return watchingMovieService.watchingMovie(movieId);
    }
}
