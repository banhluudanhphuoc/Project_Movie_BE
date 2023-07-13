package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Model.Request.RatingRequest;
import edu.kits.movie.Model.Response.RatingResponse;
import edu.kits.movie.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Api.UserApi.USER_BASE)
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    @PostMapping("rating")
    public ResponseEntity<RatingResponse> rating(@RequestBody @Valid RatingRequest request){
        return ResponseEntity.ok(ratingService.rating(request));
    }
}
