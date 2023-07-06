package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Favorite;
import edu.kits.movie.Domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}