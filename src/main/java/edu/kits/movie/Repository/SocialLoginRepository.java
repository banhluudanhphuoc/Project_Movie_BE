package edu.kits.movie.Repository;

import edu.kits.movie.Domain.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Integer> {
}