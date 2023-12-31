package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.GenresResponse;
import edu.kits.movie.Service.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
public class GenreController {
    private final GenresService genresService;
    @GetMapping("genres")
    public ResponseEntity<List<GenresResponse>> getGenres(){
        return ResponseEntity.ok(genresService.getAllGenres());
    }
}
