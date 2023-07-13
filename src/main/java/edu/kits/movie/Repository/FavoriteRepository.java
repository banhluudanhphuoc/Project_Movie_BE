package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Favorite;
import edu.kits.movie.Entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}