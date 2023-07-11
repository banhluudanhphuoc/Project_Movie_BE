package edu.kits.movie.Security;

import edu.kits.movie.Domain.Account;
import edu.kits.movie.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(username);
        if(accountOptional.isEmpty())
        throw new UsernameNotFoundException("Not found user");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Account account = accountOptional.get();
        account.getRoles().forEach((role) -> {

            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        return new User(account.getUsername(),account.getPasswords(),authorities);
    }
}
