package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.BillingPlanResponse;
import edu.kits.movie.Service.BillingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
public class BillingPlanController {
    private final BillingPlanService billingPlanService;

    @GetMapping("/billing-plan")
    public ResponseEntity<List<BillingPlanResponse>> getBillingPlans(){
        return ResponseEntity.ok(billingPlanService.getBillingPlans());
    }
}
