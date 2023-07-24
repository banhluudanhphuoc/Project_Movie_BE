package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.WriterResponse;
import org.springframework.data.domain.Pageable;

public interface WriterService {
    PaginationResponse<WriterResponse> getAllWriter(Pageable pageable);
}
