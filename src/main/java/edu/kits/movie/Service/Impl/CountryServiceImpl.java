package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.CountryResponse;
import edu.kits.movie.Entity.Country;
import edu.kits.movie.Repository.CountryRepository;
import edu.kits.movie.Service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {Exception.class})
public class CountryServiceImpl implements CountryService {
    private final ModelConverter converter;
    private final CountryRepository countryRepository;
    @Override
    public PaginationResponse<CountryResponse> getAllCountry(Pageable pageable) {
        Page<Country> countryPage = countryRepository.findAll(pageable);
        if (countryPage.getContent().isEmpty())
            return new PaginationResponse<>();
        return new PaginationResponse<>(
                countryPage.getNumber(),
                countryPage.getSize(),
                countryPage.getTotalPages(),
                converter.mapAllByIterator(countryPage.getContent(), CountryResponse.class)
        );
    }
}
