package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.base.MovieDTO;
import ru.is_lab1.dto.request.MovieRequest;
import ru.is_lab1.dto.request.upload.UploadMovie;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.service.CoordinatesService;
import ru.is_lab1.service.PersonService;

@ApplicationScoped
public class MovieMapper {

    @Inject
    PersonService personService;

    @Inject
    CoordinatesService coordinatesService;

    @Inject
    private ModelMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(MovieMapper.class);

    public Movie toEntity(MovieRequest request){
        if (request == null){
            return null;
        }
        Movie movie = new Movie();
        map(request, movie);
        mapDependency(request, movie);
        return movie;
    }

    public void updateEntity(MovieRequest request, Movie movie){
        if (request == null || movie == null){
            return;
        }
        map(request, movie);
    }

    public Movie uploadToEntity(UploadMovie uploadMovie){
        if (uploadMovie == null)
            return null;
        Movie movie = new Movie();
        mapper.map(uploadMovie, movie);
        return movie;
    }

    private void map(MovieRequest request, Movie movie){
        mapIndependent(request, movie);
        mapDependency(request, movie);
    }

    private void mapIndependent(MovieRequest request, Movie movie){
        movie.setName(request.getName());
        movie.setOscarsCount(request.getOscarsCount());
        movie.setBudget(request.getBudget());
        movie.setTotalBoxOffice(request.getTotalBoxOffice());
        movie.setMpaaRating(request.getMpaaRating());
        movie.setLength(request.getLength());
        movie.setGoldenPalmCount(request.getGoldenPalmCount());
        movie.setUsaBoxOffice(request.getUsaBoxOffice());
        movie.setGenre(request.getGenre());
    }

    private void mapDependency(MovieRequest request, Movie movie){
        movie.setCoordinates(coordinatesService.getCoordinatesById(request.getCoordinates()));
        movie.setDirector(personService.getPersonById(request.getDirector()));
        movie.setOperator(personService.getPersonById(request.getOperator()));
        if (request.getScreenwriter() != null){
            movie.setScreenwriter(personService.getPersonById(request.getScreenwriter()));
        }
    }

    private void mapIndependentUpload(UploadMovie uploadMovie, Movie movie){
        movie.setName(uploadMovie.getName());
        movie.setOscarsCount(uploadMovie.getOscarsCount());
        movie.setBudget(uploadMovie.getBudget());
        movie.setTotalBoxOffice(uploadMovie.getTotalBoxOffice());
        movie.setMpaaRating(uploadMovie.getMpaaRating());
        movie.setLength(uploadMovie.getLength());
        movie.setGoldenPalmCount(uploadMovie.getGoldenPalmCount());
        movie.setUsaBoxOffice(uploadMovie.getUsaBoxOffice());
        movie.setGenre(uploadMovie.getGenre());
    }

//    private void mapDependencyUpload(UploadMovie request, Movie movie){
//
//    }
}
