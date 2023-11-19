package com.project.HealthTrackManagement.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRecommendationDto
{
    private String _id;
    private String name;
    private String image;
    private String categories;
    private String timings;
    private Integer noOfCalories;
}
