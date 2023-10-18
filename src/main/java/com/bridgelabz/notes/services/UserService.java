package com.bridgelabz.notes.services;

import java.io.IOException;
import java.util.List;
import com.bridgelabz.notes.entities.User;
import com.bridgelabz.notes.payload.LoginDTO;
import com.bridgelabz.notes.payload.Response;
import com.bridgelabz.notes.payload.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    //create
    User createUser(UserDto userDto);
    //update
    User updateUser(UserDto userDto,Integer userId);
    //delete
    void deleteUser(Integer userId);
    //get
    User getUser(Integer userId);
    //getAll
    List<User> getAllUser();

    //user login
    Response userLogin(LoginDTO loginDTO);
    User verify(String token);
    User getUserById(Integer userId);
    User updateProfile(MultipartFile image, String token) throws IOException;

    Response getProfile(String token);
}
