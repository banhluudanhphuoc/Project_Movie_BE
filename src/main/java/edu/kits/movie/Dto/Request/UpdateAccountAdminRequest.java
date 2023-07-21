package edu.kits.movie.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountAdminRequest {
    private String username;
    private String fullName;
    private String address;
    private String avatar;
    private LocalDate dateOfBirth;
    private String email;
}
