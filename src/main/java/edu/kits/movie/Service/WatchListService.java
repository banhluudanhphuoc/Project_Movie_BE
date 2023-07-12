package edu.kits.movie.Service;

import edu.kits.movie.Model.Response.WatchListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WatchListService {
    List<WatchListResponse> getUserWatchList(Pageable pageable);
    void deleteWatchList(Integer movieId);

    void addToWatchList(Integer movieId);
}
