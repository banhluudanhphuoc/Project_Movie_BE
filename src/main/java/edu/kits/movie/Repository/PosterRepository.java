package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Integer> {
}