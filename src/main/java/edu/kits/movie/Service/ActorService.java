package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.ActorAdminResponse;
import edu.kits.movie.Dto.Response.ActorResponse;
import org.springframework.data.domain.Pageable;

public interface ActorService {
    PaginationResponse<ActorAdminResponse> getAllActor(Pageable pageable);
}
