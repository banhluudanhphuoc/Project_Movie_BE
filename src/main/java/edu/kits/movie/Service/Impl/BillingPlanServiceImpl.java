package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Response.BillingPlanResponse;
import edu.kits.movie.Entity.BillingPlan;
import edu.kits.movie.Repository.BillingPlanRepository;
import edu.kits.movie.Service.BillingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class BillingPlanServiceImpl implements BillingPlanService {
    private final ModelConverter converter;
    private final BillingPlanRepository billingPlanRepository;

    @Override
    public List<BillingPlanResponse> getBillingPlans() {
        List<BillingPlan> billingPlans = billingPlanRepository.findAll();
        if(billingPlans.isEmpty())
            return new ArrayList<>();
        return converter.mapAllByIterator(billingPlans,BillingPlanResponse.class);
    }
}
