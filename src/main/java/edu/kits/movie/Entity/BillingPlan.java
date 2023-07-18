package edu.kits.movie.Entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "billing_plan")
public class BillingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_plan_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "billing_plan_name")
    private String billingPlanName;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration_text", length = 100)
    private String durationText;

    @Column(name = "duration_in_months")
    private Long durationInMonth;

    @OneToMany(mappedBy = "billingPlan")
    private Set<Account> accounts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "billingPlan")
    private Set<Order> orders = new LinkedHashSet<>();

}