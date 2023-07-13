package edu.kits.movie.Entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class WatchListId implements Serializable {
    private static final long serialVersionUID = -583194442542852064L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WatchListId entity = (WatchListId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, username);
    }

}