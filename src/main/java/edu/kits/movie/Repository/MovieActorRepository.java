package edu.kits.movie.Repository;

import edu.kits.movie.Domain.MovieActor;
import edu.kits.movie.Domain.MovieActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorId> {
}