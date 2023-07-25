package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponse implements Serializable {
    private Integer id;
    private String movieName;
    private String video;
    private String mainPoster;
    private String billingPlan_billingPlanName;
    private String billingPlan_id;
    private Integer averageRatingPoint;
    private Date releasedDate;
    private String description;
    private List<GenresResponse> genres;
    private boolean isSeries;
    private String banner;
}
