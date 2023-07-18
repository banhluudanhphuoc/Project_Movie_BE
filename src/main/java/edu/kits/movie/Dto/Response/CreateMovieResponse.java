package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieResponse {
    private Integer billingPlan_id;
    private Integer writer_id;
    private Integer director_id;
    private Integer country_id;
    private String movieName;
    private String description;
    private String runningTime;
    private String  video;
    private String mainPoster;
    private LocalDateTime premiereDate;
    private Date releasedDate;
}
