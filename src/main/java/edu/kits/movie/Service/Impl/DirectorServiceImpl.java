package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.DirectorResponse;
import edu.kits.movie.Entity.Director;
import edu.kits.movie.Repository.DirectorRepository;
import edu.kits.movie.Service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {Exception.class})
public class DirectorServiceImpl implements DirectorService {
    private final ModelConverter converter;
    private final DirectorRepository directorRepository;
    @Override
    public PaginationResponse<DirectorResponse> getAllDirector(Pageable pageable) {
        Page<Director> directorPage = directorRepository.findAll(pageable);
        if (directorPage.getContent().isEmpty())
            return new PaginationResponse<>();
        return new PaginationResponse<>(
                directorPage.getNumber(),
                directorPage.getSize(),
                directorPage.getTotalPages(),
                converter.mapAllByIterator(directorPage.getContent(), DirectorResponse.class)
        );
    }
}
