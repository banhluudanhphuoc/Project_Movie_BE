package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailResponse implements Serializable {
    private Integer id;
    private String movieName;
    private String description;
    private String movieLength;
    private String video;
    private String mainPoster;
    private String billingPlan_billingPlanName;
    private String billingPlan_id;
    private String country_countryName;
    private String director_directorName;
    private String writer_writerName;
    private boolean isSeries;
    private List<ActorResponse> actors;
    private List<GenresResponse> genres;
    private List<TrailerResponse> movieTrailers;
    private List<PosterResponse> posters;
}
