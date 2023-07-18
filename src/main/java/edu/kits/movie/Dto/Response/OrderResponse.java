package edu.kits.movie.Dto.Response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link edu.kits.movie.Entity.Order} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse implements Serializable {
    private Integer id;
    private LocalDate orderedAt;
    private Double amount;
    private String billingPlanName;
    private String duration;
    private String account_username;
    private String paymentStatus_statusName;
    private String paymentMethod_methodName;
//    private LocalDateTime duration;
}