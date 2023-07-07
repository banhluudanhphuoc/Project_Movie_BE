package edu.kits.movie.Domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Column(name = "country_name", length = 100)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private Set<Actor> actors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "country")
    private Set<Director> directors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "country")
    private Set<Movie> movies = new LinkedHashSet<>();

}