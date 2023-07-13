package edu.kits.movie.Repository;

import edu.kits.movie.Entity.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Integer> {
}