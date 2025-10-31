package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.PersonRequest;
import ru.is_lab1.entity.Person;
import ru.is_lab1.mapper.PersonMapper;
import ru.is_lab1.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);
    @Inject
    PersonRepository repository;

    @Inject
    PersonMapper mapper;

    @Transactional
    public Person createPerson(PersonRequest request){
        logger.info("PersonService.cretePerson");
        Person createdPerson = mapper.toEntity(request);
        return repository.save(createdPerson);
    }

    public Person getPersonById(Long id){
        logger.info("PersonService.getPersonById");
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isEmpty()){
            throw new RuntimeException("Person not found");
        }
        return personOptional.get();
    }

    public List<Person> getPagePersons(int page, int size){
        logger.info("PersonService.getPagePersons");
        return repository.findPage(page, size);
    }

    @Transactional
    public Person updatePerson(Long id, PersonRequest request){
        logger.info("PersonService.updatePerson");
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isEmpty()){
            throw new RuntimeException("Person not found");
        }
        Person updatedPerson = personOptional.get();
        mapper.updatePerson(request, updatedPerson);
        return repository.update(updatedPerson);
    }

    @Transactional
    public void deletePerson(Long id){
        logger.info("PersonService.deletePerson");
        if (!repository.delete(id)){
            throw new RuntimeException("Person not found");
        }
    }
}
