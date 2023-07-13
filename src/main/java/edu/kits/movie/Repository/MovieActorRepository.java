package edu.kits.movie.Repository;

import edu.kits.movie.Entity.MovieActor;
import edu.kits.movie.Entity.MovieActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorId> {
}