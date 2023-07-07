package edu.kits.movie.Domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id", nullable = false)
    private Integer id;

    @Column(name = "writer_name", length = 100)
    private String writerName;

    @OneToMany(mappedBy = "writer")
    private Set<Movie> movies = new LinkedHashSet<>();

}