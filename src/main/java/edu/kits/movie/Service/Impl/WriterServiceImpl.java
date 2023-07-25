package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.WriterResponse;
import edu.kits.movie.Entity.Writer;
import edu.kits.movie.Repository.WriterRepository;
import edu.kits.movie.Service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {Exception.class})
public class WriterServiceImpl implements WriterService {
    private final ModelConverter converter;
    private final WriterRepository writerRepository;

    @Override
    public PaginationResponse<WriterResponse> getAllWriter(Pageable pageable) {
        Page<Writer> writerPage = writerRepository.findAll(pageable);
        if (writerPage.getContent().isEmpty())
            return new PaginationResponse<>();
        return new PaginationResponse<>(
                writerPage.getNumber(),
                writerPage.getSize(),
                writerPage.getTotalPages(),
                converter.mapAllByIterator(writerPage.getContent(), WriterResponse.class)
        );
    }
}
