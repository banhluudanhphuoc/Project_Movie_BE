package edu.kits.movie.Controller.Account;

import edu.kits.movie.Service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("username") String username,
                                         @RequestParam("verificationCode") String verificationCode) {
        emailVerificationService.verifyEmail(username, verificationCode);
        return ResponseEntity.ok("Email Verification Successful");
    }
}

