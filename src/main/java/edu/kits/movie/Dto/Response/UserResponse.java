package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
    private String username;
    private Instant billingPlanExpiredAt;
    private String billingPlan_billingPlanName;
    private Integer billingPlan_id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private LocalDate dateOfBirth;
    private String email;
}
