package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Entity.Account;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Entity.Rating;
import edu.kits.movie.Entity.RatingId;
import edu.kits.movie.Dto.Request.RatingRequest;
import edu.kits.movie.Dto.Response.RatingResponse;
import edu.kits.movie.Repository.RatingRepository;
import edu.kits.movie.Service.RatingService;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final ModelConverter converter;
    private final UserService userService;

    @Override
    public RatingResponse rating(RatingRequest ratingRequest) {
        String username = userService.getCurrentUser();
        if (ratingRequest != null && username != null) {
            RatingId ratingId = new RatingId();
            Account account = new Account();
            account.setUsername(username);
            Movie movie = new Movie();
            movie.setId(ratingRequest.getMovie_id());
            ratingId.setUsername(username);
            ratingId.setMovieId(ratingRequest.getMovie_id());

            Rating rating = new Rating();
            rating.setRatingPoint(ratingRequest.getRatingPoint());
            rating.setId(ratingId);
            rating.setAccount(account);
            rating.setMovie(movie);
            return converter.map(ratingRepository.save(rating), RatingResponse.class);
        }
        return null;
    }
}
