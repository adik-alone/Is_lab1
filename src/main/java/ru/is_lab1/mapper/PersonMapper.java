package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.PersonRequest;
import ru.is_lab1.entity.Person;
import ru.is_lab1.repository.LocationRepository;
import ru.is_lab1.repository.PersonRepository;
import ru.is_lab1.service.LocationService;

@ApplicationScoped
public class PersonMapper {
    private final Logger logger = LoggerFactory.getLogger(PersonMapper.class);
    @Inject
    private LocationService locationService;

    public Person toEntity(PersonRequest request){
        logger.info("PersonMapper.toEntity: start");
        if (request == null){
            return null;
        }
        Person person = new Person();

        person.setName(request.getName());
        person.setEyeColor(request.getEyeColor());
        person.setHairColor(request.getHairColor());
        person.setLocation(locationService.getLocationById(request.getLocation()));
        person.setHeight(request.getHeight());
        person.setWeight(request.getWeight());
        person.setPassportID(request.getPassportID());
        person.setNationality(request.getNationality());
        logger.info("PersonMapper.toEntity: newPerosn = " + person);
        logger.info("PersonMapper.toEntity: end");
        return person;
    }

    public void updatePerson(PersonRequest request, Person person){
        logger.info("PersonMapper.updatePerson: start");
        if (request == null || person == null){
            return;
        }
        logger.info("PersonMapper.updatePerson: oldPerosn = " + person);
        person.setName(request.getName());
        person.setEyeColor(request.getEyeColor());
        person.setHairColor(request.getHairColor());
        person.setLocation(locationService.getLocationById(request.getLocation()));
        person.setHeight(request.getHeight());
        person.setWeight(request.getWeight());
        person.setPassportID(request.getPassportID());
        person.setNationality(request.getNationality());
        logger.info("PersonMapper.updatePerson: newPerson = " + person);
        logger.info("PersonMapper.updatePerson: start");
    }

}
