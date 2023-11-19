package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.FoodRecommendation;
import com.project.HealthTrackManagement.Repository.IExerciseRecommendationRepository;
import com.project.HealthTrackManagement.Repository.IFoodRecommendationRepository;
import com.project.HealthTrackManagement.Service.IFoodRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodRecommendationService implements IFoodRecommendationService
{
    @Autowired
    private IFoodRecommendationRepository foodRecommendationRepository;

    @Override
    public FoodRecommendation addFoodRecommendation(FoodRecommendation foodRecommendation) {
        return foodRecommendationRepository.save(foodRecommendation);
    }

    @Override
    public List<FoodRecommendation> getAllFoodRecommendation() {
        return foodRecommendationRepository.findAll();
    }

    @Override
    public FoodRecommendation getFoodRecommendationById(String id) {
        return foodRecommendationRepository.findById(id).get();
    }

    @Override
    public FoodRecommendation getFoodRecommendationByCategories(String categories) {
        return foodRecommendationRepository.getFoodFoodRecommendationByCategories(categories);
    }

    @Override
    public FoodRecommendation updateFoodRecommendation(FoodRecommendation foodRecommendation) {
        return foodRecommendationRepository.save(foodRecommendation);
    }

    @Override
    public void deleteFoodRecommendationById(String id) {
        foodRecommendationRepository.deleteById(id);
    }
}
