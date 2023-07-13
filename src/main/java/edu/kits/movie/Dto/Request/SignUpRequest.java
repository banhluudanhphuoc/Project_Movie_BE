package edu.kits.movie.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String username ;
    private String password ;
    private int billing_plan_id;
    private String full_name;

    private String address ;

    private int phone_number ;
    private String avatar ;

    private LocalDate dateOfBirth;

    private String email;

}
