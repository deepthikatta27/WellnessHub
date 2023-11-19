package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Model.UserFoodTracking;
import com.project.HealthTrackManagement.Service.Impl.UserFoodTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/userFoodTracking")
public class UserFoodTrackingController {
    @Autowired
    private UserFoodTrackingService userFoodTrackingService;

    @PostMapping
    private ResponseEntity<?> addUserFoodTracking(@RequestBody UserFoodTracking userFoodTracking) {
        try {
            userFoodTrackingService.addUserFoodTracking(userFoodTracking);
            return new ResponseEntity<>(Map.of("msg", "User Food Tracking Added Successfully"), HttpStatus.OK);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database access error occurred while saving the attendance.");
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the user Food tracking details");
        }
    }

    @GetMapping
    private ResponseEntity<?> getAllUserFoodTracking() {
        try {
            List<UserFoodTracking> userFoodTracking = userFoodTrackingService.getAllUserFoodTracking();
            System.out.println(userFoodTracking);
            return new ResponseEntity<>(userFoodTracking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Fetch the user food Tracking details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserFoodTrackingDetailsById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userFoodTrackingService.getUserFoodTrackingById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User Food Tracking details are not found for the provided id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<?> updateUserFoodTrackingDetails(@RequestBody UserFoodTracking userFoodTracking) {
        try {
            userFoodTrackingService.updateUserFoodTracking(userFoodTracking);
            return new ResponseEntity<>(Map.of("msg", "User Food Tracking Details updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An Error Occurring while updating the User Food Tracking details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUserFoodTrackingDetailsById(@PathVariable String id) {
        try {
            userFoodTrackingService.deleteUserFoodTrackingById(id);
            return new ResponseEntity<>(Map.of("msg", "USer Food Tracking Details are deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to found the user Food Tracking details for provided id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chart/{id}")
    private ResponseEntity<?> getChart(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userFoodTrackingService.getChart(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User Exercise Tracking details are not found for the provided id" + id, HttpStatus.NOT_FOUND);
        }
    }
}
