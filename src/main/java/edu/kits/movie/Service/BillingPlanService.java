package edu.kits.movie.Service;

import edu.kits.movie.Dto.Response.BillingPlanResponse;

import java.util.List;

public interface BillingPlanService {

    List<BillingPlanResponse> getBillingPlans();
}
