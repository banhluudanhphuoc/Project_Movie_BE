package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Request.CommentRequest;
import edu.kits.movie.Dto.Response.CommentResponse;
import edu.kits.movie.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
public class CommentController {
    private final CommentService commentService;

    @PostMapping("reviewing")
    public ResponseEntity<CommentResponse> rating(@RequestBody @Valid CommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    @GetMapping("reviewing/{movieId}")
    public ResponseEntity<List<CommentResponse>> rating(@PathVariable Integer movieId) {
        return ResponseEntity.ok(commentService.getComments(movieId));
    }
}
