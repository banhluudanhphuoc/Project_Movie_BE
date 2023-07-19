package edu.kits.movie.Service.Admin;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Request.CreateMovieRequest;
import edu.kits.movie.Dto.Response.CreateMovieResponse;
import edu.kits.movie.Entity.*;
import edu.kits.movie.Repository.*;
import edu.kits.movie.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    public CreateMovieResponse createMovie(CreateMovieRequest request,
                                           MultipartFile video,
                                           MultipartFile mainPoster,
                                           List<MultipartFile> posters,
                                           List<MultipartFile> trailers) {
        try {
            String namePoster = fileStorageService.save(video);
            String nameVideo = fileStorageService.save(mainPoster);
            Movie movie = converter.map(request, Movie.class);
            movie.setVideo(nameVideo);
            movie.setMainPoster(namePoster);
            movie.setIsDeleted(false);
            CreateMovieResponse createMovieResponse = converter.map(movieRepository.save(movie), CreateMovieResponse.class);
            //add all posters of movie
            if (!posters.isEmpty()) {
                posters.forEach((poster) ->{
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
                    trailers.forEach((trailer) ->{
                        MovieTrailer movieTrailer = new MovieTrailer();
                        movieTrailer.setMovie(movie);
                        try {
                            movieTrailer.setTrailerName(fileStorageService.save(trailer));
                            movieTrailerRepository.save(movieTrailer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

            }

            return createMovieResponse;
        } catch (Exception e) {
            return null;
        }
    }


}
