package edu.kits.movie.Service;

import edu.kits.movie.Model.Request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(LoginRequest request);
}
