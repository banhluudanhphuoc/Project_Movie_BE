package edu.kits.movie.Repository;

import edu.kits.movie.Entity.WatchList;
import edu.kits.movie.Entity.WatchListId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, WatchListId> {
    List<WatchList> findAllByAccountUsername(String username, Pageable pageable);

    void deleteByAccountUsernameAndMovieId(String username, Integer movieId);

    @Query("select case when (w.movie.id=:movieId) " +
            "then true else false end from WatchList w where w.account.username=:username and w.movie.id=:movieId")
    Boolean isInWatchList(Integer movieId, String username);
}