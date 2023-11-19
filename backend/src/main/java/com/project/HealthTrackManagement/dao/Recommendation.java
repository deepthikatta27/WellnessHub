package com.project.HealthTrackManagement.dao;

import com.project.HealthTrackManagement.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {
    private String _id;
    private String exercise_period;
    private String totalLooseCalories;
    private String createdAt;
    private User user;
    private Excercise excercise;
}


@Getter
@Setter
class Excercise {
//    private String _id;
    private Integer noOfCalories;
    private Integer weight;
    private Integer height;
    private String exerciseImages;
    private String exerciseName;
}