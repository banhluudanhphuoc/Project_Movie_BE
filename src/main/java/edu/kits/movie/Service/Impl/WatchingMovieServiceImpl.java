package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Response.EpisodeResponse;
import edu.kits.movie.Dto.Response.SeasonResponse;
import edu.kits.movie.Dto.Response.WatchingVideoEpisodeResponse;
import edu.kits.movie.Dto.Response.WatchingVideoResponse;
import edu.kits.movie.Entity.Account;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Repository.MovieRepository;
import edu.kits.movie.Service.UserService;
import edu.kits.movie.Service.WatchingMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class WatchingMovieServiceImpl implements WatchingMovieService {
    private final ModelConverter converter;
    private final MovieRepository movieRepository;
    private final UserService userService;
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> watchingMovie(Integer movieId) {
        String username = userService.getCurrentUser();
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (username != null && movie != null) {
            Account account = accountRepository.findByUsername(username);
            String movieBillingPlan = movie.getBillingPlan().getBillingPlanName();
            String accountBillingPlan = account.getBillingPlan().getBillingPlanName();
            if (movieBillingPlan.equalsIgnoreCase("Premium") &&
                    accountBillingPlan.equalsIgnoreCase("free")) {
                return ResponseEntity.badRequest().body("Please upgrade to Premium to watch this film!");
            } else if (accountBillingPlan.equalsIgnoreCase("premium")
                    && account.getBillingPlanExpiredAt().isBefore(LocalDateTime.now()))
                return ResponseEntity.badRequest().body("Your premium plan is out of date!");

            if (!movie.isSeries()) {
                return ResponseEntity.ok(converter.map(movie, WatchingVideoResponse.class));
            } else {
                WatchingVideoEpisodeResponse watchingVideoEpisodeResponse = new WatchingVideoEpisodeResponse();
                List<EpisodeResponse> episodeResponse = converter.mapAllByIterator(movie.getMovieEpisode(), EpisodeResponse.class);
                List<SeasonResponse> seasonResponses = movie.getMovieEpisode()
                        .stream()
                        .map((e) -> converter.map(e.getSeason(), SeasonResponse.class))
                        .distinct()
                        .toList();
                watchingVideoEpisodeResponse.setId(movie.getId());
                watchingVideoEpisodeResponse.setMovieEpisode(episodeResponse);
                watchingVideoEpisodeResponse.setSeasons(seasonResponses);
                watchingVideoEpisodeResponse.setSeries(movie.isSeries());
                return ResponseEntity.ok(watchingVideoEpisodeResponse);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
