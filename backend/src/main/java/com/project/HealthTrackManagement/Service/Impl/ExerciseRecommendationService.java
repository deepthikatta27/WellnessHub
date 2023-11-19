package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.ExerciseRecommendation;
import com.project.HealthTrackManagement.Repository.IExerciseRecommendationRepository;
import com.project.HealthTrackManagement.Service.IExerciseRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseRecommendationService implements IExerciseRecommendationService
{
    @Autowired
    private IExerciseRecommendationRepository exerciseRecommendationRepository;

    @Override
    public ExerciseRecommendation addExerciseRecommendation(ExerciseRecommendation exerciseRecommendation) {
        return exerciseRecommendationRepository.save(exerciseRecommendation);
    }

    @Override
    public List<ExerciseRecommendation> getAllExerciseRecommendation() {
        return exerciseRecommendationRepository.findAll();
    }

    @Override
    public ExerciseRecommendation getExerciseRecommendationById(String id) {
        return exerciseRecommendationRepository.findById(id).get();
    }

    @Override
    public ExerciseRecommendation updateExerciseRecommendation(ExerciseRecommendation exerciseRecommendation) {
        return exerciseRecommendationRepository.save(exerciseRecommendation);
    }

    @Override
    public void deleteExerciseRecommendation(String id) {
        exerciseRecommendationRepository.deleteById(id);
    }

    @Override
    public List<ExerciseRecommendation> getExerciseRecommendationByHeightAndWeight(Integer height, Integer weight) {
        return exerciseRecommendationRepository.getExerciseRecommendationByHeightAndWeight(height,weight);
    }
}
