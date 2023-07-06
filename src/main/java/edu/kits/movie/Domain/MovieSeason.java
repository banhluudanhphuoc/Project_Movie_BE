package edu.kits.movie.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_season")
public class MovieSeason {
    @EmbeddedId
    private MovieSeasonId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @MapsId("seasionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seasion_id", nullable = false)
    private Season seasion;

    @Column(name = "rating_point")
    private Integer ratingPoint;

}