package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import ru.is_lab1.dto.request.PersonRequest;
import ru.is_lab1.entity.Person;
import ru.is_lab1.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {
//    @Inject
//    PersonRepository repository;
//
//    @Inject
//    ModelMapper modelMapper;
//
//    @Transactional
//    public Person createPerson(PersonRequest request){
//        Person createdPerson = modelMapper.map(request, Person.class);
//        return repository.save(createdPerson);
//    }
//
//    public Person getPersonById(Long id){
//        Optional<Person> personOptional = repository.findById(id);
//        if (personOptional.isEmpty()){
//            throw new RuntimeException("Person not found");
//        }
//        return personOptional.get();
//    }
//
//    public List<Person> getPagePersons(int page, int size){
//        return repository.findPage(page, size);
//    }
//
//    @Transactional
//    public Person updatePerson(Long id, PersonRequest request){
//        Optional<Person> personOptional = repository.findById(id);
//        if (personOptional.isEmpty()){
//            throw new RuntimeException("Person not found");
//        }
//        Person updatedPerson = personOptional.get();
//        modelMapper.map(request, updatedPerson);
//        return repository.update(updatedPerson);
//    }
//
//    @Transactional
//    public void deletePerson(Long id){
//        if (!repository.delete(id)){
//            throw new RuntimeException("Person not found");
//        }
//    }
}
