package edu.kits.movie.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponse implements Serializable {
    private Integer id;
    private String movieName;
    private String description;
    private String movieLength;
    private String video;
    private String mainPoster;
    private String billingPlan_billingPlanName;
    private String billingPlan_id;
}