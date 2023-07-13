package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Rating;
import edu.kits.movie.Entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
}