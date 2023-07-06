package edu.kits.movie.Repository;

import edu.kits.movie.Domain.WatchList;
import edu.kits.movie.Domain.WatchListId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, WatchListId> {
}