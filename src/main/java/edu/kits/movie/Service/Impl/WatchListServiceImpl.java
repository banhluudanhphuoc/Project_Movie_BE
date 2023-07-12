package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Domain.Account;
import edu.kits.movie.Domain.Movie;
import edu.kits.movie.Domain.WatchList;
import edu.kits.movie.Domain.WatchListId;
import edu.kits.movie.Model.Response.WatchListResponse;
import edu.kits.movie.Repository.WatchListRepository;
import edu.kits.movie.Service.UserService;
import edu.kits.movie.Service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class WatchListServiceImpl implements WatchListService {
    private final WatchListRepository watchListRepository;
    private final UserService userService;
    private final ModelConverter converter;

    @Override
    public List<WatchListResponse> getUserWatchList(Pageable pageable) {
        String username = userService.getCurrentUser();
        if (username != null) {
            List<WatchList> watchList = watchListRepository.findAllByAccountUsername(username, pageable);
            if (!watchList.isEmpty())
                return converter.mapAllByIterator(watchList, WatchListResponse.class);
        }

        return new ArrayList<>();
    }

    @Override
    public void deleteWatchList(Integer movieId) {
        String username = userService.getCurrentUser();
        if (movieId != null && username != null)
            watchListRepository.deleteByAccountUsernameAndMovieId(username, movieId);
    }

    @Override
    public void addToWatchList(Integer movieId) {
        String username = userService.getCurrentUser();
        System.out.println(movieId);
        if (movieId != null && username != null) {
            Account account = new Account();
            Movie movie = new Movie();
            account.setUsername(username);
            movie.setId(movieId);

            WatchListId watchListId = new WatchListId();
            watchListId.setUsername(username);
            watchListId.setMovieId(movieId);

            WatchList watchList = new WatchList();
            watchList.setId(watchListId);
            watchList.setMovie(movie);
            watchList.setAccount(account);
            watchListRepository.save(watchList);
        }

    }
}
