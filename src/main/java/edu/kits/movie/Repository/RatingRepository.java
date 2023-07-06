package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Rating;
import edu.kits.movie.Domain.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
}