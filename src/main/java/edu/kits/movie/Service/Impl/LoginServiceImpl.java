package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Entity.Account;
import edu.kits.movie.Dto.Request.LoginRequest;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Security.JWT.JwtUtils;
import edu.kits.movie.Service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = {Exception.class})
public class LoginServiceImpl implements LoginService {
    private final ModelConverter converter;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        Map<String,String> loginResponse = new HashMap<>();
        if(request != null){
            Account account = accountRepository.findById(request.getUsername()).orElse(null);
            if(account == null){
                loginResponse.put("error","Username or password is not correct");
                loginResponse.put("status","400");
                return ResponseEntity.badRequest().body(loginResponse);

            }
            if(!passwordEncoder.matches(request.getPassword(),account.getPasswords())){
                loginResponse.put("error","Username or password is not correct");
                loginResponse.put("status","400");
                return ResponseEntity.badRequest().body(loginResponse);
            }
            if(!account.getIsActive())
                return ResponseEntity.badRequest().body("Your account banned!");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            String access_token = jwtUtils.createToken(authentication);
            String refresh_token = jwtUtils.createRefreshToken(account.getUsername());
            accountRepository.updateRefreshToken(refresh_token,account.getUsername());
            loginResponse.put("access_token",access_token);
            loginResponse.put("status","200");
        }
        return ResponseEntity.ok(loginResponse);
    }
}
