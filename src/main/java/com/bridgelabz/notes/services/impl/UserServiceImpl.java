package com.bridgelabz.notes.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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

    //create
    @Override
    public User createUser(UserDto userDto) {
        User userData = new User(userDto);
        return userRepo.save(userData);
    }

    //new Update api
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user = this.userRepo.save(user);
        return this.UserToDto(user);
    }

    //delete
    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow();
        this.userRepo.delete(user);
    }

    //get
    @Override
    public UserDto getUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow();
        return this.UserToDto(user);
    }

    //get all
    
    public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
        List<UserDto> allNotes= users.stream().map((user)->this.UserToDto(user)).collect(Collectors.toList());
        return allNotes;
    }

    //login
    public UserDto userLogin(String email, String password) {
        User user= this.userRepo.findByEmailAndPassword(email,password);
        return this.UserToDto(user);
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

	public UserDto UserToDto(User user ) {
		UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
    

