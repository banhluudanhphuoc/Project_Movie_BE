package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.DirectorResponse;
import org.springframework.data.domain.Pageable;

public interface DirectorService {
    PaginationResponse<DirectorResponse> getAllDirector(Pageable pageable);
}
