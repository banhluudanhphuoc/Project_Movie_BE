package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Response.GenresResponse;
import edu.kits.movie.Repository.GenreRepository;
import edu.kits.movie.Service.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {
    private final ModelConverter converter;
    private final GenreRepository genreRepository;

    @Override
    public List<GenresResponse> getAllGenres() {
        return converter.mapAllByIterator(genreRepository.findAll(), GenresResponse.class);
    }
}
