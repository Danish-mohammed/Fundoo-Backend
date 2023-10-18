package com.bridgelabz.notes.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.apache.commons.io.FilenameUtils;
import com.bridgelabz.notes.controllers.UserController;
import com.bridgelabz.notes.exception.*;
import com.bridgelabz.notes.payload.LoginDTO;
import com.bridgelabz.notes.payload.Response;
import com.bridgelabz.notes.util.Constant;
import com.bridgelabz.notes.util.EmailService;
import com.bridgelabz.notes.util.TokenUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.notes.entities.User;
import com.bridgelabz.notes.payload.UserDto;
import com.bridgelabz.notes.repositories.UserRepo;
import com.bridgelabz.notes.services.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

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
        // Save the User entity
        userData = userRepo.save(userData);
        System.out.println(userData);
        System.out.println("User ID: " + userData.getUser_id());
        String token = tokenUtility.createToken(userData.getUser_id());
        emailService.sendEmail(userDto.getEmail(),Constant.EMAIL_SUBJECT_VERIFY,
                Constant.BASE_URL + Constant.VERIFY_URI + "?token=" + token);
        return userData;
    }

    public User verify(String token) {
        int userId = tokenUtility.decodeToken(token);
        LOG.info(Constant.SERVICE_VERIFY_USER_METHOD);
        User user = userRepo.findAll().stream().filter(i -> i.getUser_id().equals(userId)).findAny().orElse(null);
        if (user == null) {
            LOG.error(Constant.FAILED_TO_VERIFY);
            throw new RegisterVerifyException(Constant.FAILED_TO_VERIFY);
        }
        user.setActive(true);
        LOG.info(Constant.SUCCESS_VERIFY);
        return userRepo.save(user);
    }

    //new Update api
    @Override
    public User updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
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

    @Override
    public User updateProfile(MultipartFile image, String token) throws IOException {
        System.out.println(image);
        int userId = tokenUtility.decodeToken(token);
        LOG.info(Constant.SERVICE_UPDATE_UPLOAD_PROFILE);
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            LOG.info(userId + Constant.EMAIL_NOT_FOUND);
            throw new ForgotPasswordException(userId + Constant.EMAIL_NOT_FOUND);
        }

        if (image != null && image.getContentType() != null
                && !image.getContentType().toLowerCase().startsWith("image"))
            throw new UserException(Constant.IMAGE_FORMAT_EXCEPTION);

        byte[] bytes = image.getBytes();
        String extension = image.getContentType().replace("image/", "");
        String fileLocation = Constant.UPLOAD_FOLDER + userId + "." + extension;
        System.out.println("Location" + fileLocation);
        Path path = Paths.get(fileLocation);
        System.out.println("Path" + path);
        Files.write(path, bytes);
        user.setProfile(fileLocation);
        return userRepo.save(user);
    }

    @Override
    public Response getProfile(String token) {
        LOG.info(Constant.SERVICE_GET_UPLOAD_PROFILE);
        int userId = tokenUtility.decodeToken(token);
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException(Constant.EMAIL_NOT_FOUND);
        }
        String images = "";
        String filePath = Constant.UPLOAD_FOLDER;
        File fileFolder = new File(filePath);
        if (fileFolder != null) {
            for (final File file : fileFolder.listFiles()) {
                if (!file.isDirectory()) {
                    String encodeBase64 = null;
                    try {
                        if ((Constant.UPLOAD_FOLDER + file.getName()).equals(user.get().getProfile())) {
                            String extension = FilenameUtils.getExtension(file.getName());
                            FileInputStream fileInputStream = new FileInputStream(file);
                            byte[] bytes = new byte[(int) file.length()];
                            fileInputStream.read(bytes);
                            encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                            images = ("data:image/" + extension + ";base64," + encodeBase64);
                            fileInputStream.close();
                            break;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return new Response(200, Constant.GET_IMAGES_RESPONSE, images);
    }
}
    

