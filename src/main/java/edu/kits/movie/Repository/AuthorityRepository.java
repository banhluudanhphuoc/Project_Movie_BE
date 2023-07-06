package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Authority;
import edu.kits.movie.Domain.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}