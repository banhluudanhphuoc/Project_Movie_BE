package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}