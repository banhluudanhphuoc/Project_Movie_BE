package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}