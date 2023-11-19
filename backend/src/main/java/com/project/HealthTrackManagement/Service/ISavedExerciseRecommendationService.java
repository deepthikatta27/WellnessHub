package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.SavedExerciseRecommendation;

import java.util.List;

public interface ISavedExerciseRecommendationService
{
    SavedExerciseRecommendation addSavedExerciseRecommendation(SavedExerciseRecommendation savedExerciseRecommendation);

    List<SavedExerciseRecommendation> getAllSavedExerciseRecommendations();

    SavedExerciseRecommendation getSavedExerciseRecommendationById(String id);

    SavedExerciseRecommendation updatedSavedExerciseRecommendation(SavedExerciseRecommendation savedExerciseRecommendation );

    void deleteSavedExerciseRecommendationById(String id);

    SavedExerciseRecommendation getSavedExerciseRecommendationByUserId(String userId);
}
