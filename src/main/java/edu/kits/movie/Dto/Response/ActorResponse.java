package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponse implements Serializable {
    private Integer id;
    private String actorName;
    private String avatar;
    private String description;
    private LocalDate dateOfBirth;
    private String country_countryName;
}
