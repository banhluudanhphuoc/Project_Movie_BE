package edu.kits.movie.Service;

import edu.kits.movie.Dto.Request.RatingRequest;
import edu.kits.movie.Dto.Response.RatingResponse;

public interface RatingService {
    RatingResponse rating(RatingRequest ratingRequest);
}
