package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Authority;
import edu.kits.movie.Entity.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}