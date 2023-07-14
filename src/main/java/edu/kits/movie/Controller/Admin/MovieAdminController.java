package edu.kits.movie.Controller.Admin;


import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Service.Admin.MovieAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(Api.AdminApi.USER_ADMIN)
@RequestMapping("/api/admin")
public class MovieAdminController {

    private final MovieAdminService movieAdminService;

        @Autowired
        public MovieAdminController(MovieAdminService movieAdminService) {
            this.movieAdminService = movieAdminService;
        }

        @PostMapping("/movies")

        public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
            Movie addedMovie = movieAdminService.addMovie(movie);
            return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
        }

    }


