package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}