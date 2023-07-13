package edu.kits.movie.Repository;

import edu.kits.movie.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Modifying
    @Query(value = "Update account a set a.refresh_token =:newRefreshToken where a.username=:username",nativeQuery = true)
    void updateRefreshToken(String newRefreshToken, String username);


    Account findByUsername(String username);

}