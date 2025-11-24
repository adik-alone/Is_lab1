package ru.is_lab1.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.postgresql.jdbc.PreferQueryMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.UserRequest;
import ru.is_lab1.entity.User;
import ru.is_lab1.exceptions.RepositoryException;
import ru.is_lab1.mapper.UserMapper;
import ru.is_lab1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository repository;

    @Inject
    private UserMapper mapper;

    @Transactional
    public User createUser(UserRequest request){
        User user = mapper.toEntity(request);
        return repository.save(user);
    }

    public User getUserById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public List<User> getPageUser(int page, int size){
        return repository.findPage(page, size);
    }

    @Transactional
    public User updateUser(UserRequest request, Long id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new RepositoryException("User with this id not found");
        User updatedUser = user.get();
        if (!mapper.updateUser(request, updatedUser))
            throw new RepositoryException("Update fail");
        return updatedUser;
    }

    @Transactional
    public void delete(Long id){
        if(!repository.delete(id)){
            throw new RepositoryException("User not found");
        }
    }
}
