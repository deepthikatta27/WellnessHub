package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.FoodRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRecommendationRepository extends MongoRepository<FoodRecommendation,String>
{
    FoodRecommendation getFoodFoodRecommendationByCategories(String Categories);
}
