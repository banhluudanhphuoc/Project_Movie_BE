package edu.kits.movie.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table( name = "movie_episode")
@Entity
@Getter
@Setter
public class MovieEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_episode_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @Column(name = "description")
    private String description;

    @Column(name = "video")
    private String video;
}
