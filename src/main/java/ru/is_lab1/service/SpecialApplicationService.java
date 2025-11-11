package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.Person;
import ru.is_lab1.entity.enums.MovieGenre;
import ru.is_lab1.repository.MovieRepository;
import ru.is_lab1.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SpecialApplicationService {
    private final Logger logger = LoggerFactory.getLogger(SpecialApplicationService.class);
    @Inject
    MovieRepository repository;

    @Inject
    PersonRepository personRepository;

    @Transactional
    public void deleteOneMovieGenre(MovieGenre genre){
        logger.info("SpecialService.deleteOneMovieGenre: start");
        Optional<List<Movie>> optionalListMovie = repository.getOneMovieByGenre(genre.toString());
        logger.info("SpecialService.deleteOneMovieGenre: got optional movie");
        if (optionalListMovie.isEmpty()){
            throw new RuntimeException("Movie with this genre not found");
        }
//        if (optionalMovie.isEmpty()){
//            throw new RuntimeException("Movie with this genre not found");
//        }
        List<Movie> movies = optionalListMovie.get();
        if (movies.isEmpty()){
            throw new RuntimeException("Movie with this genre not found");
        }
        logger.info("SpecialService.deleteOneMovieGenre: try get movie ");
        Movie deletingMovie = movies.get(0);
        logger.info("SpecialService.deleteOneMovieGenre: current Movie ==== " + deletingMovie);
        if (!repository.delete(deletingMovie.getId())){
            throw new RuntimeException("Deleting ends with error, try again");
        }
        logger.info("SpecialService.deleteOneMovieGenre: end");
    }

    public List<Long> groupMovieByTotalBoxOffice(){
        logger.info(" ===== SpecialService.groupMovieByTotalBoxOffice: run ======");
        return repository.groupMovieByTotalBoxOffice();
    }

    public Long movieWithGoldenPalmEqualsValue(int value){
        logger.info(" ================== SpecialService.movieWithGoldenPalmEqualsValue: run ===================");
        Optional<Long> count = repository.movieWithGoldenPalmEqualsValue(value);
        return count.orElse(0L);
    }

    public List<Person> getDirectorsWithoutOscars(){
        logger.info(" =================== SpecialService.getDirectorsWithoutOscars: run ==================================");
        return personRepository.directorsWithoutOscars();
    }
//
//    public void reBalanceOscarsInOtherGenre(){
//
//    }

}
