package edu.kits.movie.Repository;

import edu.kits.movie.Entity.BillingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingPlanRepository extends JpaRepository<BillingPlan, Integer> {
}