package edu.kits.movie.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse implements Serializable {
    private Integer id;
    private String description;
    private String account_fullName;
    private String account_avatar;
    private LocalDateTime createdAt;
}
