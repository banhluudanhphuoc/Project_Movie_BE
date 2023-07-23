package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchingVideoEpisodeResponse implements Serializable {
    private Integer id;
    private List<SeasonResponse> seasons;
    private List<EpisodeResponse> movieEpisode;
    private boolean isSeries;
}
