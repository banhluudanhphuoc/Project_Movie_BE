package edu.kits.movie.Service.Admin;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Response.CreateMovieResponse;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MovieAdminService {
    private final MovieRepository movieRepository;
    private final ModelConverter converter;

    public CreateMovieResponse createMovie(CreateMovieRequest request) {
        Movie movie = converter.map(request, Movie.class);
        movie.setIsDeleted(false);
        return converter.map(movieRepository.save(movie), CreateMovieResponse.class);
    }


}
