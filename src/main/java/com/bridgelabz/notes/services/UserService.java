package com.bridgelabz.notes.services;

import java.util.List;

import com.bridgelabz.notes.entities.User;
import com.bridgelabz.notes.payload.UserDto;

public interface UserService {
    //create
    User createUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto,Integer userId);
    //delete
    void deleteUser(Integer userId);
    //get
    UserDto getUser(Integer userId);
    //getAll
    List<UserDto> getAllUser();

    //user login
    UserDto userLogin(String email, String password);

    User getUserById(Integer userId);
}
