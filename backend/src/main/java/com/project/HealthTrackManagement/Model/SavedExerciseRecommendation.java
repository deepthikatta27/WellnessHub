package com.project.HealthTrackManagement.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedExerciseRecommendation
{
    private String _id;
    private String userId;
    private String exerciseRecommendationId;
}
