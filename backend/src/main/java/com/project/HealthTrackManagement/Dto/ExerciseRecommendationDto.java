package com.project.HealthTrackManagement.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRecommendationDto
{
    private String _id;
    private String exerciseName;
    private String exerciseImages;
    private Integer height;
    private Integer weight;
    private Integer noOfCalories;
}
