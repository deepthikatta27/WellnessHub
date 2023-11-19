package com.project.HealthTrackManagement.Controller;


import com.project.HealthTrackManagement.Model.SavedFoodRecommendation;
import com.project.HealthTrackManagement.Service.Impl.SavedFoodRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/savedFoodRecommendation")
public class SavedFoodRecommendationController {
    @Autowired
    private SavedFoodRecommendationService savedFoodRecommendationService;

    @PostMapping
    private ResponseEntity<?> addSavedFoodRecommendationService(@RequestBody SavedFoodRecommendation savedFoodRecommendation) {
        try {
            SavedFoodRecommendation existingSavedFoodRecommendation = savedFoodRecommendationService.getSavedFoodRecommendationByUserId(savedFoodRecommendation.getUserId());
            if (existingSavedFoodRecommendation != null) {
                return new ResponseEntity<>(Map.of("msg", "Saved Food Recommendation already exists for the provided user"), HttpStatus.BAD_REQUEST);
            }
            savedFoodRecommendationService.addSavedFoodRecommendation(savedFoodRecommendation);
            return new ResponseEntity<>(Map.of("msg", "Saved Food Recommendation added Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("msg" , e.getMessage()));
        }
    }

    @GetMapping
    private ResponseEntity<?> getAllSavedExerciseRecommendations() {
        List<SavedFoodRecommendation> savedFoodRecommendations = savedFoodRecommendationService.getAllSavedFoodRecommendations();
        return new ResponseEntity<>(savedFoodRecommendations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getSavedFoodRecommendationById(@PathVariable String id) {
        SavedFoodRecommendation savedFoodRecommendation = savedFoodRecommendationService.getSavedFoodRecommendationById(id);

        if (savedFoodRecommendation != null) {
            return new ResponseEntity<>(savedFoodRecommendation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saved Food Recommendation not found for provided ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<?> updatedSavedFoodRecommendation(@RequestBody SavedFoodRecommendation savedFoodRecommendation) {
        try {
            savedFoodRecommendationService.updatedSavedFoodRecommendation(savedFoodRecommendation);
            return new ResponseEntity<>(Map.of("msg", "Saved Food Recommendations are updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to updated the saved food recommendations", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteSavedFoodRecommendation(@PathVariable String id) {
        try {
            savedFoodRecommendationService.deleteSavedFoodRecommendationById(id);
            return new ResponseEntity<>(Map.of("msg", "Saved Food Recommendations are deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Saved Food Recommendations are not found for provided id" + id, HttpStatus.NOT_FOUND);
        }
    }

}
