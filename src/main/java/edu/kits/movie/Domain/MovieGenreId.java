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
public class MovieGenreId implements Serializable {
    private static final long serialVersionUID = 1802679992668836868L;
    @Column(name = "genres_id", nullable = false)
    private Integer genresId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieGenreId entity = (MovieGenreId) o;
        return Objects.equals(this.genresId, entity.genresId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genresId, movieId);
    }

}