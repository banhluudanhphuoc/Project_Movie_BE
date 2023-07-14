package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponse implements Serializable {
    private Integer id;
    private String movieName;
    private String description;
    private String runningTime;
    private String video;
    private String mainPoster;
    private String billingPlan_billingPlanName;
    private String billingPlan_id;
    private Integer averageRatingPoint;
    private Date releasedDate;
}
