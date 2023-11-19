package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.SavedFoodRecommendation;
import com.project.HealthTrackManagement.dao.SavedFood;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISavedFoodRecommendationRepository extends MongoRepository<SavedFoodRecommendation, String> {
    @Aggregation(pipeline = {
            "{$match: {userId: ?0}}",
            "{\n" +
                    "    $addFields: {\n" +
                    "      food_id_test: {\n" +
                    "        $toObjectId: \"$foodRecommendationId\",\n" +
                    "      },\n" +
                    "      user_id_test: {\n" +
                    "        $toObjectId: \"$userId\",\n" +
                    "      },\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"foodRecommendation\",\n" +
                    "      localField: \"food_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"food\",\n" +
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
                    "      food: {\n" +
                    "        $arrayElemAt: [\"$food\", 0],\n" +
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
                    "      \"food._id\": 0,\n" +
                    "      \"food._class\": 0,\n" +
                    "    },\n" +
                    "  }"
    })
    List<SavedFood> getSavedFood(String userId);


    SavedFoodRecommendation getSavedFoodRecommendationByUserId(String userId);
}
