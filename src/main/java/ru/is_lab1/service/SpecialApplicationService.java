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
        Optional<List<Movie>> optionalListMovie = repository.getListMovieByGenre(genre.toString());
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
    @Transactional
    public void reBalanceOscarsInOtherGenre(MovieGenre genreFrom, MovieGenre genreTo){
        logger.info(" =================== SpecialService.reBalanceOscarsCountInOtherGenre: start ============================");
        logger.info(" =================== SpecialService.reBalanceOscarsCountInOtherGenre: genre from -- " + genreFrom + " ============================");
        logger.info(" =================== SpecialService.reBalanceOscarsCountInOtherGenre: genre from -- " + genreTo + " ============================");
        Optional<List<Movie>> moviesFromOptional = repository.getListMovieByGenre(genreFrom.toString());
        if (moviesFromOptional.isEmpty()){
            throw new RuntimeException("Movie List with first genre not found");
        }
        List<Movie> moviesFrom = moviesFromOptional.get();
        if (moviesFrom.isEmpty()){
            throw new RuntimeException("Movies FROM not found");
        }

        Optional<List<Movie>> moviesToOptional = repository.getListMovieByGenre(genreTo.toString());
        if (moviesToOptional.isEmpty()){
            throw new RuntimeException("Movie List with second genre not found");
        }
        List<Movie> moviesTo = moviesToOptional.get();
        if (moviesTo.isEmpty()){
            throw new RuntimeException("Movies TO not found");
        }
        long countOscars = 0L;
        for (Movie m: moviesFrom){
            countOscars += m.getOscarsCount();
        }
        long oscarsToEach = countOscars / moviesTo.size();
        if (oscarsToEach < moviesTo.size()){
            throw new RuntimeException("Not enough oscars to perform this operation");
        }
        for (Movie mTo: moviesTo){
            mTo.setOscarsCount(mTo.getOscarsCount() + oscarsToEach);
            repository.update(mTo);
        }
        for (Movie m: moviesFrom){
            m.setOscarsCount(0);
            repository.update(m);
        }
    }
}
