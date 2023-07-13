package edu.kits.movie.Service;

import edu.kits.movie.Model.Response.SeasonResponse;

import java.util.List;

public interface SeasonService {
    List<SeasonResponse> getSeasons();
}
