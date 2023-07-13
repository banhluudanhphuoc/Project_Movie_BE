package edu.kits.movie.Entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "payment_status")
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_status_id", nullable = false)
    private Integer id;

    @Column(name = "status_name", length = 100)
    private String statusName;

    @OneToMany(mappedBy = "paymentStatus")
    private Set<Order> orders = new LinkedHashSet<>();

}