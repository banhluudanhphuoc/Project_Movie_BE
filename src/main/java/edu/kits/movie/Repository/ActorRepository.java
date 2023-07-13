package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}