package edu.kits.movie.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {
    @NotNull
    private Integer billingPlan_id;
    private Integer writer_id;
    private Integer director_id;
    private Integer country_id;
    @NotBlank
    private String movieName;
    private String description;
    @NotBlank
    private String runningTime;
    @NotNull
    private LocalDateTime premiereDate;
    @NotNull
    private Date releasedDate;
    private String banner;
    private List<ActorRequest> actors;
    private List<GenresRequest> genres;
}
