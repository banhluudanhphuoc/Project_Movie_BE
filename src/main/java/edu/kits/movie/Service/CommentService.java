package edu.kits.movie.Service;

import edu.kits.movie.Model.Request.CommentRequest;
import edu.kits.movie.Model.Response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Integer movieId);
    CommentResponse createComment(CommentRequest commentRequest);
}
