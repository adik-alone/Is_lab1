package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService {
    @Inject
    private UserRepository userRepository;

    public User createUser(String name, String password){
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.delete(id);
    }
}
