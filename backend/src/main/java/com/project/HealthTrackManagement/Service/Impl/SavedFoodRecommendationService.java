package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.SavedFoodRecommendation;
import com.project.HealthTrackManagement.Repository.ISavedFoodRecommendationRepository;
import com.project.HealthTrackManagement.Service.ISavedFoodRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedFoodRecommendationService implements ISavedFoodRecommendationService {
    @Autowired
    private ISavedFoodRecommendationRepository repository;

    @Override
    public SavedFoodRecommendation addSavedFoodRecommendation(SavedFoodRecommendation savedFoodRecommendation) {
        return repository.save(savedFoodRecommendation);
    }

    @Override
    public List<SavedFoodRecommendation> getAllSavedFoodRecommendations() {
        return repository.findAll();
    }

    @Override
    public SavedFoodRecommendation getSavedFoodRecommendationById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public SavedFoodRecommendation updatedSavedFoodRecommendation(SavedFoodRecommendation savedFoodRecommendation) {
        return repository.save(savedFoodRecommendation);
    }

    @Override
    public void deleteSavedFoodRecommendationById(String id) {
        repository.deleteById(id);
    }

    @Override
    public SavedFoodRecommendation getSavedFoodRecommendationByUserId(String userId) {
        return repository.getSavedFoodRecommendationByUserId(userId);
    }
}
