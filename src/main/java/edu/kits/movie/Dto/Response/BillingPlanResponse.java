package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BillingPlanResponse implements Serializable {
    private Integer id;
    private String billingPlanName;
    private Double price;
    private String durationText;
}
