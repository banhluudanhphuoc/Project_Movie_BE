package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieEpisodeResponse implements Serializable {
    private Integer id;
    private Integer movie_id;
    private Integer season_id;
    private String description;
    private String video;
    private Integer episode;
    private String episodeName;
}
