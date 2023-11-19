package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.SavedExerciseRecommendation;
import com.project.HealthTrackManagement.Repository.ISavedExerciseRecommendationRepository;
import com.project.HealthTrackManagement.Service.ISavedExerciseRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedExerciseRecommendationService implements ISavedExerciseRecommendationService {
    @Autowired
    private ISavedExerciseRecommendationRepository savedExerciseRecommendationRepository;

    @Override
    public SavedExerciseRecommendation addSavedExerciseRecommendation(SavedExerciseRecommendation savedExerciseRecommendation) {
        return savedExerciseRecommendationRepository.save(savedExerciseRecommendation);
    }

    @Override
    public List<SavedExerciseRecommendation> getAllSavedExerciseRecommendations() {
        return savedExerciseRecommendationRepository.findAll();
    }

    @Override
    public SavedExerciseRecommendation getSavedExerciseRecommendationById(String id) {
        return savedExerciseRecommendationRepository.findById(id).get();
    }

    @Override
    public SavedExerciseRecommendation updatedSavedExerciseRecommendation(SavedExerciseRecommendation savedExerciseRecommendation) {
        return savedExerciseRecommendationRepository.save(savedExerciseRecommendation);
    }

    @Override
    public void deleteSavedExerciseRecommendationById(String id) {
        savedExerciseRecommendationRepository.deleteById(id);
    }

    @Override
    public SavedExerciseRecommendation getSavedExerciseRecommendationByUserId(String userId) {
        return savedExerciseRecommendationRepository.findByUserId(userId);
    }
}
