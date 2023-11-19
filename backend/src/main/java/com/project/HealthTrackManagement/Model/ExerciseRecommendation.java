package com.project.HealthTrackManagement.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseRecommendation
{
    private String _id;
    private String exerciseName;
    private String exerciseImages;
    private Integer height;
    private Integer weight;
    private Integer noOfCalories;
    private Collection<SavedExerciseRecommendation> savedExerciseRecommendation;

    public Collection<SavedExerciseRecommendation> getSavedExerciseRecommendation() {
        return savedExerciseRecommendation;
    }

    public void setSavedExerciseRecommendation(Collection<SavedExerciseRecommendation> savedExerciseRecommendation) {
        this.savedExerciseRecommendation = savedExerciseRecommendation;
    }
}
