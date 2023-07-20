package edu.kits.movie.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieEpisodeRequest {
    @NotNull
    private Integer movie_id;
    @NotNull
    private Integer season_id;
    private String description;
    @NotNull
    private Integer episode;
    @NotBlank
    private String episodeName;
}
