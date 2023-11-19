package com.project.HealthTrackManagement.dao;

import com.project.HealthTrackManagement.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class foodRecommendation
{
    private String _id;
    private Integer quantityInTake;
    private Integer totalGainedCalories;
    private String createdAt;
    private User user;
    private Food food;
}

@Setter
@Getter
class Food
{
    private String name;
    private String image;
    private String categories;
    private String timings;
    private String noOfCalories;
}
