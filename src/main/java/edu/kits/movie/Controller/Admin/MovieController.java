package edu.kits.movie.Controller.Admin;


import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Response.CreateMovieResponse;
import edu.kits.movie.Service.Admin.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(Api.AdminApi.ADMIN_BASE)
@RequiredArgsConstructor
public class MovieController {
    private final MovieAdminService movieAdminService;

    @PostMapping("movies")
    public ResponseEntity<CreateMovieResponse>
    createMovie(@RequestPart("request") CreateMovieRequest request,
                @RequestPart("video") MultipartFile video,
                @RequestPart("mainPoster") MultipartFile mainPoster,
                @RequestPart("posters") List<MultipartFile> posters,
                @RequestPart("trailers") List<MultipartFile> trailers
                ) {
        return ResponseEntity.ok(movieAdminService.createMovie(request,video,mainPoster,posters,trailers));
    }
}
