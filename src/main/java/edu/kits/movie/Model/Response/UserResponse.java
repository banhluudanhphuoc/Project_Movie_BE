package edu.kits.movie.Model.Response;

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
    private String fullName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private LocalDate dateOfBirth;
    private String email;
}
