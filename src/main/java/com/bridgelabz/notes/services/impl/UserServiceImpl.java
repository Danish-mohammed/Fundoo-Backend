package com.bridgelabz.notes.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.notes.exception.LoginException;
import com.bridgelabz.notes.exception.RegistrationException;
import com.bridgelabz.notes.payload.LoginDTO;
import com.bridgelabz.notes.payload.Response;
import com.bridgelabz.notes.util.Constant;
import com.bridgelabz.notes.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.notes.entities.User;
import com.bridgelabz.notes.payload.UserDto;
import com.bridgelabz.notes.repositories.UserRepo;
import com.bridgelabz.notes.services.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenUtility tokenUtility;

    //create
    @Override
    public User createUser(UserDto userDto) {
        User user = userRepo.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new RegistrationException(userDto.getEmail() + " " + "Constant.REGISTER_EMAIL_FOUND");
        }
        User userData = new User(userDto);
        return userRepo.save(userData);
    }

    //new Update api
    @Override
    public User updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepo.save(user);

    }

    //delete
    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow();
        this.userRepo.delete(user);
    }

    //get
    @Override
    public User getUser(Integer userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    //get all
    
    public List<User> getAllUser() {
        return userRepo.findAll().stream().toList();
    }

    //login
    public Response userLogin(LoginDTO loginDTO) {
        User user;
        if (userRepo.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
                && i.getPassword().matches(loginDTO.getPassword())
                && i.isActive())) {
            user = userRepo.findByEmail(loginDTO.getEmail());
            return new Response(200, Constant.SUCCESS_LOGIN,tokenUtility.createToken(user.getUser_id()));
        } else {
            throw new LoginException(Constant.FAILED_LOGIN);
        }
    }

    //new code for getUserById method
    @Override
    public User getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElse(null);
        // If the user is not found, return null or throw an exception based on your requirements
        if (user == null) {
            return null;
        }
        // Convert the User entity to a UserDto
        return user;
    }

    public User DtoToUser(UserDto userDto ) {
		User user= new User();
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
        System.out.println(user);
		return user;
	}
}
    

