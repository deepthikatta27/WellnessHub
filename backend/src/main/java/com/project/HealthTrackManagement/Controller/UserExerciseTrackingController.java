package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Model.UserExerciseTracking;
import com.project.HealthTrackManagement.Model.UserFoodTracking;
import com.project.HealthTrackManagement.Service.Impl.UserExerciseTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/userExerciseTracking")
public class UserExerciseTrackingController {
    @Autowired
    private UserExerciseTrackingService userExerciseTrackingService;

    @PostMapping
    private ResponseEntity<?> addUserExerciseTracking(@RequestBody UserExerciseTracking userExerciseTracking) {
        try {
            userExerciseTrackingService.addUserExerciseTracking(userExerciseTracking);
            return new ResponseEntity<>(Map.of("msg", "User Exercise Tracking Added Successfully"), HttpStatus.OK);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database access error occurred while saving the User Exercise Tracking details.");
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the user Exercise tracking details");
        }
    }

    @GetMapping
    private ResponseEntity<?> getAllUserExerciseTracking() {
        try {
            List<UserExerciseTracking> userExerciseTracking = userExerciseTrackingService.getAllUserExerciseTracking();
            return new ResponseEntity<>(userExerciseTracking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Fetch the user Exercise Tracking details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserExerciseTrackingDetailsById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userExerciseTrackingService.getUserExerciseTrackingById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User Exercise Tracking details are not found for the provided id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<?> updateUserFoodTrackingDetails(@RequestBody UserExerciseTracking userExerciseTracking) {
        try {
            userExerciseTrackingService.updateUserExerciseTracking(userExerciseTracking);
            return new ResponseEntity<>(Map.of("msg", "User Exercise Tracking Details updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An Error Occurring while updating the User Exercise Tracking details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUserExerciseTrackingDetailsById(@PathVariable String id) {
        try {
            userExerciseTrackingService.deleteUserExerciseTrackingById(id);
            return new ResponseEntity<>(Map.of("msg", "User Exercise Tracking Details are deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to found the user Exercise Tracking details for provided id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chart/{id}")
    private ResponseEntity<?> getChart(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userExerciseTrackingService.getChart(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User Exercise Tracking details are not found for the provided id" + id, HttpStatus.NOT_FOUND);
        }
    }
}
