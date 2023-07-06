package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Model.Request.LoginRequest;
import edu.kits.movie.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final ModelConverter converter;
    @Override
    public String login(LoginRequest request) {
        return null;
    }
}
