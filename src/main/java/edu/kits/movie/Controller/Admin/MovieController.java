package edu.kits.movie.Controller.Admin;


import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Response.CreateMovieResponse;
import edu.kits.movie.Service.Admin.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Api.AdminApi.ADMIN_BASE)
@RequiredArgsConstructor
public class MovieController {
    private final MovieAdminService movieAdminService;
    @PostMapping("/movies")
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody CreateMovieRequest request){
        return ResponseEntity.ok(movieAdminService.createMovie(request));
    }
}
