package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Domain.Account;
import edu.kits.movie.Domain.Comment;
import edu.kits.movie.Domain.Movie;
import edu.kits.movie.Model.Request.CommentRequest;
import edu.kits.movie.Model.Response.CommentResponse;
import edu.kits.movie.Repository.CommentRepository;
import edu.kits.movie.Service.CommentService;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class CommentServiceImpl implements CommentService {
    private final ModelConverter converter;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public List<CommentResponse> getComments(Integer movieId) {
        if (movieId != null) {
            List<Comment> comments = commentRepository.findAllByMovieId(movieId);
            if (comments.isEmpty())
                return new ArrayList<>();
            return converter.mapAllByIterator(comments, CommentResponse.class);
        }
        return new ArrayList<>();
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        String username = userService.getCurrentUser();
        if(username != null && commentRequest != null){
            Account account = new Account();
            account.setUsername(username);
            Movie movie = new Movie();
            movie.setId(commentRequest.getMovieId());
            Comment comment = new Comment();
            comment.setAccount(account);
            comment.setMovie(movie);
            comment.setDescription(commentRequest.getDescription());
           return converter.map(commentRepository.save(comment),CommentResponse.class);
        }
        return null;
    }
}
