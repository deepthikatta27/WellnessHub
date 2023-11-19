package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Dto.UserDto;
import com.project.HealthTrackManagement.Model.User;
import com.project.HealthTrackManagement.Repository.ISavedExerciseRecommendationRepository;
import com.project.HealthTrackManagement.Repository.ISavedFoodRecommendationRepository;
import com.project.HealthTrackManagement.Repository.IUserExerciseTrackingRepository;
import com.project.HealthTrackManagement.Repository.IUserFoodTrackingRepository;
import com.project.HealthTrackManagement.Service.Impl.UserService;
import com.project.HealthTrackManagement.dao.Recommendation;
import com.project.HealthTrackManagement.dao.SavedExercise;
import com.project.HealthTrackManagement.dao.SavedFood;
import com.project.HealthTrackManagement.dao.foodRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private IUserExerciseTrackingRepository exerciseTrackingRepository;

    @Autowired
    private IUserFoodTrackingRepository foodTrackingRepository;

    @Autowired
    private ISavedExerciseRecommendationRepository repository;

    @Autowired
    private ISavedFoodRecommendationRepository savedFoodRecommendationRepository;

    @PostMapping
    private ResponseEntity<?> addUser(String _id, String name, MultipartFile image, String email, String password, Integer age, Integer height, Integer weight) {
        String filepath = Paths.get("").toAbsolutePath().toString();
        Path originalFilepath = Paths.get(filepath, "src", "main", "resources", "static", "Images", image.getOriginalFilename());

        String host = "http://localhost:8080/Images/" + image.getOriginalFilename();

        try {
            image.transferTo(originalFilepath);
        } catch (IOException ioe) {
            return new ResponseEntity<>("Failed to save user's photo. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = User.builder()
                ._id(_id)
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .height(height)
                .weight(weight)
                .image(host)
                .build();

        try {
            User existingUser = userService.findByEmail(email);

            if (existingUser != null) {
                return new ResponseEntity<>(Map.of("msg", "User with email " + email + " already exists."), HttpStatus.BAD_REQUEST);
            }

            userService.addUser(user);
            return new ResponseEntity<>(Map.of("msg", "Student Added Successfully"), HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error adding student to the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/exerciseTracking")
    private ResponseEntity<?> getAllUsers() {
        try {
            List<Recommendation> recommendations = exerciseTrackingRepository.getRecommendations();
            return ResponseEntity.ok(recommendations);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error accessing the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.set_id(user.get_id());
        userDto.setName(user.getName());
        userDto.setImage(user.getImage());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAge(user.getAge());
        userDto.setHeight(user.getHeight());
        userDto.setWeight(user.getWeight());

        return userDto;
    }

    @GetMapping("/savedExercise")
    private ResponseEntity<?> getAllSavedExercise(@RequestParam String userId) {
        try {
            List<SavedExercise> recommendations = repository.getSavedExercise(userId);
            return ResponseEntity.ok(recommendations);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error accessing the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private UserDto convertToDto2(User user2) {
        UserDto userDto = new UserDto();
        userDto.set_id(user2.get_id());
        userDto.setName(user2.getName());
        userDto.setImage(user2.getImage());
        userDto.setEmail(user2.getEmail());
        userDto.setPassword(user2.getPassword());
        userDto.setAge(user2.getAge());
        userDto.setHeight(user2.getHeight());
        userDto.setWeight(user2.getWeight());

        return userDto;
    }


    @GetMapping("/foodTracking")
    private ResponseEntity<?> getAllUsersByFood() {
        try {
            List<foodRecommendation> recommendations = foodTrackingRepository.foodRecommendation();
            return ResponseEntity.ok(recommendations);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error accessing the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private UserDto convertToDto1(User user1) {
        UserDto userDto = new UserDto();
        userDto.set_id(user1.get_id());
        userDto.setName(user1.getName());
        userDto.setImage(user1.getImage());
        userDto.setEmail(user1.getEmail());
        userDto.setPassword(user1.getPassword());
        userDto.setAge(user1.getAge());
        userDto.setHeight(user1.getHeight());
        userDto.setWeight(user1.getWeight());

        return userDto;
    }

    @GetMapping("/savedFood")
    private ResponseEntity<?> getAllFoodBySaved(@RequestParam String userId) {
        try {
            List<SavedFood> recommendations = savedFoodRecommendationRepository.getSavedFood(userId);
            return ResponseEntity.ok(recommendations);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private UserDto convertToDto3(User user3) {
        UserDto userDto = new UserDto();
        userDto.set_id(user3.get_id());
        userDto.setName(user3.getName());
        userDto.setImage(user3.getImage());
        userDto.setEmail(user3.getEmail());
        userDto.setPassword(user3.getPassword());
        userDto.setAge(user3.getAge());
        userDto.setHeight(user3.getHeight());
        userDto.setWeight(user3.getWeight());

        return userDto;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            User user = userService.getUSerById(id);

            UserDto userDto = new UserDto();

            userDto.set_id(user.get_id());
            userDto.setName(user.getName());
            userDto.setImage(user.getImage());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAge(user.getAge());
            userDto.setHeight(user.getHeight());
            userDto.setWeight(user.getWeight());

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User is not found for provided id is " + id, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    private ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(Map.of("msg", "User Updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An Error Occurring while updating the User details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    private ResponseEntity<?> deleteUserById(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(Map.of("msg", "User Deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User is not found for provided id is " + id, HttpStatus.NOT_FOUND);
        }
    }
}

