package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.SavedExerciseRecommendation;
import com.project.HealthTrackManagement.Model.SavedFoodRecommendation;

import java.util.List;

public interface ISavedFoodRecommendationService {
    SavedFoodRecommendation addSavedFoodRecommendation(SavedFoodRecommendation savedFoodRecommendation);

    List<SavedFoodRecommendation> getAllSavedFoodRecommendations();

    SavedFoodRecommendation getSavedFoodRecommendationById(String id);

    SavedFoodRecommendation updatedSavedFoodRecommendation(SavedFoodRecommendation savedFoodRecommendation);

    void deleteSavedFoodRecommendationById(String id);

    SavedFoodRecommendation getSavedFoodRecommendationByUserId(String userId);
}
