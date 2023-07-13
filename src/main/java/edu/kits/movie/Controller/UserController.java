package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.UserResponse;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
public class UserController {
    private final UserService userService;
    @GetMapping("info")
    public ResponseEntity<UserResponse> getUserInfo(){
        return ResponseEntity.ok(userService.getUserInfo());
    }
}
