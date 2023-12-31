package edu.kits.movie.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id", nullable = false)
    private Integer id;

    @Column(name = "season_name", length = 50)
    private String seasonName;

    @OneToMany(mappedBy = "season")
    private Set<MovieEpisode> movieEpisodes = new LinkedHashSet<>();
}