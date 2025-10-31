package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.MovieRequest;
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

    private final Logger logger = LoggerFactory.getLogger(MovieMapper.class);

    public Movie toEntity(MovieRequest request){
        if (request == null){
            return null;
        }
        Movie movie = new Movie();
        map(request, movie);
        return movie;
    }

    public void updateEntity(MovieRequest request, Movie movie){
        if (request == null || movie == null){
            return;
        }
        map(request, movie);
    }

    private void map(MovieRequest request, Movie movie){
        movie.setName(request.getName());
        movie.setOscarsCount(request.getOscarsCount());
        movie.setBudget(request.getBudget());
        movie.setTotalBoxOffice(request.getTotalBoxOffice());
        movie.setMpaaRating(request.getMpaaRating());
        movie.setLength(request.getLength());
        movie.setGoldenPalmCount(request.getGoldenPalmCount());
        movie.setUsaBoxOffice(request.getUsaBoxOffice());
        movie.setGenre(request.getGenre());
        movie.setCoordinates(coordinatesService.getCoordinatesById(request.getCoordinates()));
        movie.setDirector(personService.getPersonById(request.getDirector()));
        movie.setOperator(personService.getPersonById(request.getOperator()));
        movie.setScreenwriter(personService.getPersonById(request.getScreenwriter()));
    }
}
