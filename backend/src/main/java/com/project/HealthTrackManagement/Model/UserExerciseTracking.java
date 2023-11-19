package com.project.HealthTrackManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserExerciseTracking
{
    private String _id;
    private String user_id;
    private String exercise_id;
    private String exercise_period;
    private Integer totalLooseCalories;
    private String createdAt;

}
