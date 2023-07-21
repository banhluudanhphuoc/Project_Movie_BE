package edu.kits.movie.Service;

import edu.kits.movie.Dto.Response.GenresResponse;

import java.util.List;

public interface GenresService {
    List<GenresResponse> getAllGenres();
}
