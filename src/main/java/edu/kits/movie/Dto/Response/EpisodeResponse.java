package edu.kits.movie.Dto.Response;

import edu.kits.movie.Entity.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeResponse implements Serializable {
    private Integer id;
    private SeasonResponse season;
    private String description;
    private String video;
    private String episodeName;
    private Integer episode;
}
