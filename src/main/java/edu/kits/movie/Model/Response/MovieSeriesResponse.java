package edu.kits.movie.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSeriesResponse implements Serializable {
    private Integer movie_id;
    private Integer id;
    private String description;
    private String video;
    private String movie_video;
}
