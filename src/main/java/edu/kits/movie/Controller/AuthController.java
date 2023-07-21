package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Service.AuthorUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.AdminApi.ADMIN_BASE)
public class AuthController {
    private final AuthorUserService authorUserService;
    @PatchMapping("/account")
    public ResponseEntity<?> changeRole(@RequestParam("roleId") Integer roleId){
        authorUserService.changeRole(roleId);
        return ResponseEntity.noContent().build();
    }

}
