package com.project.HealthTrackManagement.dao;

import com.project.HealthTrackManagement.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedExercise
{
    private String _id;
    private User user;
    private Exercise excercise;

}

@Getter
@Setter
class Exercise
{
    private String _id;
    private String exerciseName;
    private String exerciseImages;
    private String height;
    private String weight;
    private String noOfCalories;

}
