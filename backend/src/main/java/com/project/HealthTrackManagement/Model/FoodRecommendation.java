package com.project.HealthTrackManagement.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodRecommendation
{
    private String _id;
    private String name;
    private String image;
    private String categories;
    private String timings;
    private Integer noOfCalories;

}
