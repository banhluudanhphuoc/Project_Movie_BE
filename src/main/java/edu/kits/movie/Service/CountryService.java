package edu.kits.movie.Service;

import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.CountryResponse;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    PaginationResponse<CountryResponse> getAllCountry(Pageable pageable);
}
