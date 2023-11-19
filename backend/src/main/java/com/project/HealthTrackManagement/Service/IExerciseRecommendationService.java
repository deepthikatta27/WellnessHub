package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.ExerciseRecommendation;

import java.util.List;

public interface IExerciseRecommendationService
{
    ExerciseRecommendation addExerciseRecommendation(ExerciseRecommendation exerciseRecommendation);

    List<ExerciseRecommendation> getAllExerciseRecommendation();

    ExerciseRecommendation getExerciseRecommendationById(String id);

    ExerciseRecommendation updateExerciseRecommendation(ExerciseRecommendation exerciseRecommendation);

    void deleteExerciseRecommendation(String id);

    List<ExerciseRecommendation> getExerciseRecommendationByHeightAndWeight(Integer height,Integer weight);
}
