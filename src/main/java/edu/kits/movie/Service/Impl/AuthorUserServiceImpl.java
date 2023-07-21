package edu.kits.movie.Service.Impl;

import edu.kits.movie.Repository.AuthorityRepository;
import edu.kits.movie.Service.AuthorUserService;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class AuthorUserServiceImpl implements AuthorUserService {
    private final AuthorityRepository authorityRepository;
    private final UserService userService;

    @Override
    public void changeRole(Integer roleId) {
        String username = userService.getCurrentUser();
        if (username != null)
            authorityRepository.changeRole(roleId, username);
    }
}
