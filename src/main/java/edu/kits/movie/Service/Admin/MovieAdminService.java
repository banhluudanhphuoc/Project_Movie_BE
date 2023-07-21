package edu.kits.movie.Service.Admin;

import com.google.common.base.Joiner;
import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Common.PaginationResponse;
import edu.kits.movie.Common.SearchOperation;
import edu.kits.movie.Dto.Request.CreateMovieEpisodeRequest;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Request.UpdateMovieRequest;
import edu.kits.movie.Dto.Response.*;
import edu.kits.movie.Entity.*;
import edu.kits.movie.Repository.*;
import edu.kits.movie.Repository.Specification.MovieSpecificationBuilder;
import edu.kits.movie.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MovieAdminService {
    private final MovieRepository movieRepository;
    private final ModelConverter converter;
    private final FileStorageService fileStorageService;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieActorRepository movieActorRepository;
    private final MovieTrailerRepository movieTrailerRepository;
    private final PosterRepository posterRepository;
    private final MovieEpisodeRepository movieEpisodeRepository;

    public CreateMovieResponse createMovie(CreateMovieRequest request,
                                           String video,
                                           MultipartFile mainPoster,
                                           List<MultipartFile> posters,
                                           List<String> trailers) {
        try {
            String namePoster = fileStorageService.save(mainPoster);
            Movie movie = converter.map(request, Movie.class);
            movie.setVideo(video);
            movie.setMainPoster(namePoster);
            movie.setIsDeleted(false);
            CreateMovieResponse createMovieResponse = converter.map(movieRepository.save(movie), CreateMovieResponse.class);
            //add all posters of movie
            if (!posters.isEmpty()) {
                posters.forEach((poster) -> {
                    Poster newPoster = new Poster();
                    newPoster.setMovie(movie);
                    try {
                        newPoster.setPosterName(fileStorageService.save(poster));
                        posterRepository.save(newPoster);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            //add all genres of movie
            if (!movie.getGenres().isEmpty()) {
                movie.getGenres().forEach((genre) -> {
                    MovieGenreId movieGenreId = new MovieGenreId();
                    movieGenreId.setMovieId(createMovieResponse.getId());
                    movieGenreId.setGenresId(genre.getId());

                    MovieGenre movieGenre = new MovieGenre();
                    movieGenre.setMovie(movie);
                    movieGenre.setGenres(genre);
                    movieGenre.setId(movieGenreId);

                    movieGenreRepository.save(movieGenre);
                });
            }
            //add all actors of movie
            if (!movie.getActors().isEmpty()) {
                movie.getActors().forEach((actor) -> {
                    MovieActorId movieActorId = new MovieActorId();
                    movieActorId.setMovieId(movie.getId());
                    movieActorId.setActorId(actor.getId());

                    MovieActor movieActor = new MovieActor();
                    movieActor.setActor(actor);
                    movieActor.setId(movieActorId);
                    movieActor.setMovie(movie);

                    movieActorRepository.save(movieActor);
                });

                //add all trailers of movie
                if (!trailers.isEmpty()) {
                    trailers.forEach((trailer) -> {
                        MovieTrailer movieTrailer = new MovieTrailer();
                        movieTrailer.setMovie(movie);
                        movieTrailer.setTrailerName(trailer);
                        movieTrailerRepository.save(movieTrailer);
                    });
                }

            }
            return createMovieResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public CreateMovieEpisodeResponse createMovieEpisode(CreateMovieEpisodeRequest request, String video) {
        if (request != null) {
            MovieEpisode movieEpisode = converter.map(request, MovieEpisode.class);
            movieEpisode.setVideo(video);
            return converter.map(movieEpisodeRepository.save(movieEpisode), CreateMovieEpisodeResponse.class);
        }
        return null;
    }

    public void deleteMovie(Integer movieId) {
        if (movieId != null)
            movieRepository.delete(movieId);
    }

    public UpdateMovieResponse updateMovie(UpdateMovieRequest request, MultipartFile mainPoster) throws IOException {
        if (request != null) {
            Movie movie = converter.map(request, Movie.class);
            if (mainPoster != null)
                movie.setMainPoster(fileStorageService.save(mainPoster));
            else {
                movieRepository.findById(request.getId()).ifPresent(movieInDb -> {
                    movie.setMainPoster(movieInDb.getMainPoster());
                });
            }
            return converter.map(movieRepository.save(movie), UpdateMovieResponse.class);
        }
        return null;
    }

    public PaginationResponse<MovieResponse> getAllMovies(String search, Pageable pageable){
        Page<Movie> movies;
        MovieSpecificationBuilder builder = new MovieSpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Movie> spec = builder.build();
        movies = movieRepository.findAll(spec, pageable);
        List<MovieResponse> movieResponses;
        if (movies.isEmpty()) {
            movieResponses = new ArrayList<>();
            return new PaginationResponse<>(0, 0, 0, movieResponses);
        }
        movieResponses = converter.mapAllByIterator(movies.getContent(), MovieResponse.class);
        return new PaginationResponse<>(movies.getNumber(), movies.getSize(), movies.getTotalPages(), movieResponses);
    }

    public MovieDetailResponse getMovieDetails(Integer id) {
        if (id != null) {
            Movie movie = movieRepository.findById(id).orElse(null);
            if (movie != null) {
                return converter.map(movie, MovieDetailResponse.class);
            }
        }
        return null;
    }


}
