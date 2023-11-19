package com.project.HealthTrackManagement.dao;

import com.project.HealthTrackManagement.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedFood
{
    private String _id;
    private User user;
    private Foods food;
}

@Getter
@Setter
class Foods
{
    private String name;
    private String image;
    private String categories;
    private String timings;
    private String noOfCalories;
}
