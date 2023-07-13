package edu.kits.movie.Controller.Account;

import edu.kits.movie.Dto.Request.SignUpRequest;
import edu.kits.movie.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AccountController {


    @Autowired
        private AccountService accountService;
    private SignUpRequest signUpRequest;

    @PostMapping("/signup")
        public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        this.signUpRequest = signUpRequest;
        accountService.registerAccount(signUpRequest);
            return ResponseEntity.ok("Sign Up Success");
        }
}
