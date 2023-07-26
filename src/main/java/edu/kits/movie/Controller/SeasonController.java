package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.SeasonResponse;
import edu.kits.movie.Service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(Api.UserApi.USER_BASE)
@Validated
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping("/seasons/{movieId}")
    public ResponseEntity<List<SeasonResponse>>
    getMovieSeasons(@PathVariable(name = "movieId") @Valid @NotNull Integer movieId) {
        return ResponseEntity.ok(seasonService.getMovieSeasons(movieId));
    }

    @GetMapping("/seasons")
    public ResponseEntity<List<SeasonResponse>>
    getAllSeason() {
        return ResponseEntity.ok(seasonService.getSeasons());
    }
}
