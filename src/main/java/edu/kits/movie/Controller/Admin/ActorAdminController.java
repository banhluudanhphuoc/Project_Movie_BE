package edu.kits.movie.Controller.Admin;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.ActorAdminResponse;
import edu.kits.movie.Service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.AdminApi.ADMIN_BASE)
public class ActorAdminController {
    private final ActorService actorService;

    @GetMapping("actors")
    public ResponseEntity<PaginationResponse<ActorAdminResponse>>
    getAllActor(@RequestParam Optional<Integer> size,
                @RequestParam Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(actorService.getAllActor(pageable));
    }
}
