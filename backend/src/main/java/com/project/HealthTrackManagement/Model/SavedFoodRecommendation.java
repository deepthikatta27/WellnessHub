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
public class SavedFoodRecommendation
{
    private String _id;
    private String userId;
    private String foodRecommendationId;
}
