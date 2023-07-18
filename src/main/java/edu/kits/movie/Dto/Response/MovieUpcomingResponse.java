package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpcomingResponse {
    private Integer id;
    private String movieName;
    private String description;
    private String runningTime;
    private LocalDateTime premiereDate;
    private String mainPoster;
    private String billingPlan_billingPlanName;
    private String billingPlan_id;
    private Date releasedDate;
}
