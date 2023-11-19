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
public class UserFoodTracking
{
    private String _id;
    private String user_id;
    private String foodRecommendation_id;
    private Integer quantityInTake;
    private Integer totalGainedCalories;
    private String createdAt;
}
