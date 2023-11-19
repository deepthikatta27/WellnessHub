package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Model.ExerciseRecommendation;
import com.project.HealthTrackManagement.Model.SavedExerciseRecommendation;
import com.project.HealthTrackManagement.Model.User;
import com.project.HealthTrackManagement.Repository.ISavedExerciseRecommendationRepository;
import com.project.HealthTrackManagement.Service.Impl.ExerciseRecommendationService;
import com.project.HealthTrackManagement.Service.Impl.SavedExerciseRecommendationService;
import com.project.HealthTrackManagement.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/savedExerciseRecommendation")
public class SavedExerciseRecommendationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseRecommendationService exerciseRecommendationService;

    @Autowired
    private SavedExerciseRecommendationService savedExerciseRecommendationService;

    @Autowired
    private ISavedExerciseRecommendationRepository repository;

    @PostMapping
    private ResponseEntity<?> addSavedExerciseRecommendation(@RequestBody SavedExerciseRecommendation savedExerciseRecommendation) {
        try {
            SavedExerciseRecommendation existingSavedExerciseRecommendation = savedExerciseRecommendationService.getSavedExerciseRecommendationByUserId(savedExerciseRecommendation.getUserId());
            if (existingSavedExerciseRecommendation != null) {
                return new ResponseEntity<>(Map.of("msg", "Saved Exercise Recommendation already exists for the provided user"), HttpStatus.BAD_REQUEST);
            }
            savedExerciseRecommendationService.addSavedExerciseRecommendation(savedExerciseRecommendation);
            return new ResponseEntity<>(Map.of("msg", "Successfully add the saved exercise recommendation"), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("msg" , e.getMessage()));
        }
    }

    // New method to retrieve all saved exercise recommendations
    @GetMapping
    private ResponseEntity<?> getAllSavedExerciseRecommendations() {
        List<SavedExerciseRecommendation> savedExerciseRecommendations = savedExerciseRecommendationService.getAllSavedExerciseRecommendations();
        return new ResponseEntity<>(savedExerciseRecommendations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getSavedExerciseRecommendationById(@PathVariable String id) {
        SavedExerciseRecommendation savedExerciseRecommendation = savedExerciseRecommendationService.getSavedExerciseRecommendationById(id);

        if (savedExerciseRecommendation != null) {
            return new ResponseEntity<>(savedExerciseRecommendation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("msg", "Saved Exercise Recommendation not found for provided ID: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<?> updatedSavedExerciseRecommendation(@RequestBody SavedExerciseRecommendation savedExerciseRecommendation) {
        try {
            savedExerciseRecommendationService.updatedSavedExerciseRecommendation(savedExerciseRecommendation);
            return new ResponseEntity<>(Map.of("msg", "Saved Exercise Recommendations are updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to updated the saved exercise recommendations", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteSavedExerciseRecommendation(@PathVariable String id) {
        try {
            savedExerciseRecommendationService.deleteSavedExerciseRecommendationById(id);
            return new ResponseEntity<>(Map.of("msg", "Saved Exercise Recommendations are deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Saved Exercise Recommendations are not found for provided id" + id, HttpStatus.NOT_FOUND);
        }
    }
}
