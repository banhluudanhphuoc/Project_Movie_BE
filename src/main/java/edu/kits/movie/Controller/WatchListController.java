package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Model.Response.WatchListResponse;
import edu.kits.movie.Service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Api.UserApi.USER_BASE)
@RequiredArgsConstructor
public class WatchListController {
    private final WatchListService watchListService;

    @GetMapping("watchlist")
    public ResponseEntity<List<WatchListResponse>> getUserWatchList(
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(watchListService.getUserWatchList(pageable));
    }

    @PostMapping("watchlist")
    public ResponseEntity<?> addToWatchList(@RequestParam Integer movieId) {
        watchListService.addToWatchList(movieId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("watchlist/{movieId}")
    public ResponseEntity<?> deleteWatchList(@PathVariable Integer movieId) {
        watchListService.deleteWatchList(movieId);
        return ResponseEntity.noContent().build();
    }
}
