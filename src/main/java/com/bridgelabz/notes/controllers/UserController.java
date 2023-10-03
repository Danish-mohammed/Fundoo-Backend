package com.bridgelabz.notes.controllers;
import java.util.List;

import com.bridgelabz.notes.entities.User;
import com.bridgelabz.notes.payload.LoginDTO;
import com.bridgelabz.notes.payload.Response;
import com.bridgelabz.notes.payload.ResponseDTO;
import com.bridgelabz.notes.payload.UserDto;
import com.bridgelabz.notes.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDto user) {
        User userData = userService.createUser(user);
        ResponseDTO responseDTO = new ResponseDTO("Register Successful", userData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user,@PathVariable Integer userId){
        User userDto =  this.userService.updateUser(user, userId);
        return ResponseEntity.ok(userDto);
    }
    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    //get
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId){
        User user =  this.userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userDtos =  this.userService.getAllUser();
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDTO loginBody) {
        try {
            Response apiRes = this.userService.userLogin(loginBody);
            System.out.println(apiRes);
            return ResponseEntity.ok(apiRes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}
