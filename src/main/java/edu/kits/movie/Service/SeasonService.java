package edu.kits.movie.Service;

import edu.kits.movie.Dto.Response.SeasonResponse;

import java.util.List;

public interface SeasonService {
    List<SeasonResponse> getSeasons();
    List<SeasonResponse> getMovieSeasons(Integer movieId);
}
