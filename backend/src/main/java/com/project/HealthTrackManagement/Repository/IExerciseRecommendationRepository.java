package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.ExerciseRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExerciseRecommendationRepository extends MongoRepository<ExerciseRecommendation,String>
{
    List<ExerciseRecommendation> getExerciseRecommendationByHeightAndWeight(Integer height, Integer weight);
}
