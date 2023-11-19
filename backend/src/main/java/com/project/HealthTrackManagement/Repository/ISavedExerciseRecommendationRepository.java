package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.SavedExerciseRecommendation;
import com.project.HealthTrackManagement.dao.Recommendation;
import com.project.HealthTrackManagement.dao.SavedExercise;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISavedExerciseRecommendationRepository extends MongoRepository<SavedExerciseRecommendation, String> {
    @Aggregation(pipeline = {
            "{  $match: { userId: ?0 } }",
            "{\n" +
                    "    $addFields: {\n" +
                    "      excercise_id_test: {\n" +
                    "        $toObjectId: \"$exerciseRecommendationId\",\n" +
                    "      },\n" +
                    "      user_id_test: {\n" +
                    "        $toObjectId: \"$userId\",\n" +
                    "      },\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"exerciseRecommendation\",\n" +
                    "      localField: \"excercise_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"exercise\",\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"user\",\n" +
                    "      localField: \"user_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"user\",\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $project: {\n" +
                    "      excercise: {\n" +
                    "        $arrayElemAt: [\"$exercise\", 0],\n" +
                    "      },\n" +
                    "      user: {\n" +
                    "        $arrayElemAt: [\"$user\", 0],\n" +
                    "      },\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $project: {\n" +
                    "      \"user._id\": 0,\n" +
                    "      \"user._class\": 0,\n" +
                    "      \"exercise._id\": 0,\n" +
                    "      \"exercise._class\": 0,\n" +
                    "    },\n" +
                    "  }"
    })
    List<SavedExercise> getSavedExercise(String userId);

    SavedExerciseRecommendation findByUserId(String userId);
}
