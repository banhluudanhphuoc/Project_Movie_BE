package edu.kits.movie.Controller.Admin;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Request.UpdateAccountAdminRequest;
import edu.kits.movie.Dto.Response.AccountAdminResponse;
import edu.kits.movie.Service.Admin.AccountAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.AdminApi.ADMIN_BASE)
public class AccountAdminController {
    private final AccountAdminService accountAdminService;
    @GetMapping("accounts")
    public ResponseEntity<PaginationResponse<AccountAdminResponse>>
    getAllAccounts(@RequestParam Optional<Integer> page,
                   @RequestParam Optional<Integer> size ){
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10) );
        return ResponseEntity.ok(accountAdminService.getAllAccounts(pageable));
    }

    @PutMapping("accounts")
    public ResponseEntity<AccountAdminResponse>
    updateAccount(@RequestBody UpdateAccountAdminRequest request){
        return ResponseEntity.ok(accountAdminService.updateAccount(request));
    }

    @DeleteMapping("accounts")
    public ResponseEntity<?> deleteAccount(@RequestParam String username){
        accountAdminService.delete(username);
        return ResponseEntity.noContent().build();
    }
}
