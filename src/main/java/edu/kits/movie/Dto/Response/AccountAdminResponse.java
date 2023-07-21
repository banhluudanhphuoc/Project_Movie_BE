package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAdminResponse {
    private String username;
    private Boolean isActive;
    private String fullName;
    private String address;
    private String avatar;
    private LocalDate dateOfBirth;
    private String email;
}
