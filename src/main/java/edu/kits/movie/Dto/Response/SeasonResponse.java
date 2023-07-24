package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResponse implements Serializable {
    private Integer id;
    private String seasonName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonResponse seasonResponse = (SeasonResponse) o;
        return id == seasonResponse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
