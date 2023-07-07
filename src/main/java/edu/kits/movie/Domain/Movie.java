package edu.kits.movie.Domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer id;

    @Column(name = "movie_name", nullable = false, length = 100)
    private String movieName;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "movie_length", nullable = false, length = 50)
    private String movieLength;

    @Column(name = "video", nullable = false, length = 100)
    private String video;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "main_poster", nullable = false, length = 100)
    private String mainPoster;

    @Column(name = "isdeleted", nullable = false)
    private Boolean isdeleted = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Account> accounts = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Genre> genres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieSeason> movieSeasons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieTrailer> movieTrailers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Poster> posters = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<WatchList> watchLists = new LinkedHashSet<>();

}