package ru.is_lab1.mapper;

import ru.is_lab1.dto.request.UserRequest;
import ru.is_lab1.entity.User;

public class UserMapper {

    public User toEntity(UserRequest request){
        if (request == null){
            return null;
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        return user;
    }

    public boolean updateUser(UserRequest request, User user){
        if (request == null || user == null)
            return false;
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        return true;
    }
}
