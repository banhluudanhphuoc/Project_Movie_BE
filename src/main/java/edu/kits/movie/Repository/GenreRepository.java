package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}