package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Modifying(flushAutomatically = true)
    @Query(value = "Update account a set a.refresh_token =:newRefreshToken where a.username=:username",nativeQuery = true)
    void updateRefreshToken(String newRefreshToken, String username);
    @Modifying(flushAutomatically = true)
    @Query(value = "update account a set a.is_active = false where a.username=:username",nativeQuery = true)
    void delete(String username);

    Account findByUsername(String username);

    Optional<Account> findByEmail(String email);

}