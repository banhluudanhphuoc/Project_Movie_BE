package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpcomingDetailResponse {
    private Integer id;
    private String movieName;
    private String description;
    private String runningTime;
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
    private Date releasedDate;
    private LocalDateTime premiereDate;
    private String banner;
}
