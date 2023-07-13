package edu.kits.movie.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
    @NotNull
    private Integer ratingPoint;
    @NotNull
    private Integer movie_id;
}
