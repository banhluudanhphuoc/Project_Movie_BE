package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Entity.Account;
import edu.kits.movie.Dto.Response.UserResponse;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;
    private final ModelConverter converter;

    @Override
    public String getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.isBlank())
            return username;
        return null;
    }

    @Override
    public UserResponse getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findById(username).orElse(null);
        if (account != null)
            return converter.map(account, UserResponse.class);
        return null;
    }
}
