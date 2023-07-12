package edu.kits.movie.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchListResponse implements Serializable {
    private MovieResponse movie;
    private Double seekTo;
}
