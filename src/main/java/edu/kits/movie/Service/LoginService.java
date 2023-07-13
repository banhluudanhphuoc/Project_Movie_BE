package edu.kits.movie.Service;

import edu.kits.movie.Dto.Request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(LoginRequest request);
}
