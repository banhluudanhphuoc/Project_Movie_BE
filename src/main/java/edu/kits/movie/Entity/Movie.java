package edu.kits.movie.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
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

    @Column(name = "running_time", nullable = false, length = 50)
    private String runningTime;

    @Column(name = "video", nullable = false, length = 100)
    private String video;

    @Column(name = "premiere_date")
    private LocalDateTime premiereDate;

    @Column(name = "released_date")
    private Date releasedDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "main_poster", nullable = false, length = 100)
    private String mainPoster;

    @Column(name = "isdeleted", nullable = false)
    private Boolean isDeleted;

    @JoinColumn(name = "billing_plan_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BillingPlan billingPlan;

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

    @OneToMany(mappedBy = "movie")
    private Set<MovieEpisode> movieEpisode = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Account> accounts = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Genre> genres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieTrailer> movieTrailers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Poster> posters = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<WatchList> watchLists = new LinkedHashSet<>();

    @Formula("(SELECT AVG(r.rating_point) FROM rating r where r.movie_id = movie_id)")
    private Double averageRatingPoint;

    public boolean isSeries() {
        for (Genre genre : genres) {
            if (genre.getGenresName().equalsIgnoreCase("series"))
                return true;
        }
        return false;
    }
}