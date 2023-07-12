package edu.kits.movie.Repository;

import edu.kits.movie.Domain.WatchList;
import edu.kits.movie.Domain.WatchListId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, WatchListId> {
    List<WatchList> findAllByAccountUsername(String username, Pageable pageable);
    void deleteByAccountUsernameAndMovieId(String username, Integer movieId);

}