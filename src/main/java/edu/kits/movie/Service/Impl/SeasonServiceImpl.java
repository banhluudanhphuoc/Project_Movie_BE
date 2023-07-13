package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Domain.Season;
import edu.kits.movie.Model.Response.SeasonResponse;
import edu.kits.movie.Repository.SeasonRepository;
import edu.kits.movie.Service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class SeasonServiceImpl implements SeasonService {
    private final ModelConverter converter;
    private final SeasonRepository seasonRepository;

    @Override
    public List<SeasonResponse> getSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        if(!seasons.isEmpty())
            return converter.mapAllByIterator(seasons, SeasonResponse.class);
        return new ArrayList<>();
    }
}
