package edu.kits.movie.Domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MovieSeasonId implements Serializable {
    private static final long serialVersionUID = 9025147826862040515L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "seasion_id", nullable = false)
    private Integer seasionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieSeasonId entity = (MovieSeasonId) o;
        return Objects.equals(this.seasionId, entity.seasionId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasionId, movieId);
    }

}