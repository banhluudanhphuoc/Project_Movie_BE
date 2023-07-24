package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Dto.Response.ActorAdminResponse;
import edu.kits.movie.Dto.Response.ActorResponse;
import edu.kits.movie.Entity.Actor;
import edu.kits.movie.Repository.ActorRepository;
import edu.kits.movie.Service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {Exception.class})
public class ActorServiceImpl implements ActorService {
    private final ModelConverter converter;
    private final ActorRepository actorRepository;

    @Override
    public PaginationResponse<ActorAdminResponse> getAllActor(Pageable pageable) {
        Page<Actor> actorPage = actorRepository.findAll(pageable);
        if (actorPage.getContent().isEmpty())
            return new PaginationResponse<>();
        return new PaginationResponse<>(
                actorPage.getNumber(),
                actorPage.getSize(),
                actorPage.getTotalPages(),
                converter.mapAllByIterator(actorPage.getContent(), ActorAdminResponse.class)
        );
    }
}
