package edu.kits.movie.Service;

import edu.kits.movie.Model.Request.RatingRequest;
import edu.kits.movie.Model.Response.RatingResponse;

public interface RatingService {
    RatingResponse rating(RatingRequest ratingRequest);
}
