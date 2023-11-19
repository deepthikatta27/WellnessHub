package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.FoodRecommendation;

import java.util.List;

public interface IFoodRecommendationService
{
    FoodRecommendation addFoodRecommendation(FoodRecommendation foodRecommendation);

    List<FoodRecommendation> getAllFoodRecommendation();

    FoodRecommendation getFoodRecommendationById(String id);

    FoodRecommendation getFoodRecommendationByCategories(String categories);

    FoodRecommendation updateFoodRecommendation(FoodRecommendation foodRecommendation);

    void deleteFoodRecommendationById(String id);
}
