package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.UserFoodTracking;
import com.project.HealthTrackManagement.dao.ChartItem;
import com.project.HealthTrackManagement.dao.foodRecommendation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserFoodTrackingRepository extends MongoRepository<UserFoodTracking,String>
{

    @Aggregation(pipeline = {
            "{\n" +
                    "    $addFields: {\n" +
                    "      food_id_test: {\n" +
                    "        $toObjectId: \"$foodRecommendation_id\",\n" +
                    "      },\n" +
                    "      user_id_test: {\n" +
                    "        $toObjectId: \"$user_id\",\n" +
                    "      },\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"foodRecommendation\",\n" +
                    "      localField: \"food_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"food\"\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"user\",\n" +
                    "      localField: \"user_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"user\"\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $project: {\n" +
                    "      quantityInTake:1,\n" +
                    "      totalGainedCalories:1,\n" +
                    "      createdAt:1,\n" +
                    "      food:{\n" +
                    "        $arrayElemAt: [\"$food\", 0],\n" +
                    "      },\n" +
                    "      user:{\n" +
                    "        $arrayElemAt: [\"$user\", 0],\n" +
                    "      },\n" +
                    "    },\n" +
                    "  },",
            "{\n" +
                    "    $project: {\n" +
                    "      \"user._id\": 0,\n" +
                    "  \t\t\"user._class\": 0,\n" +
                    "  \t\t\"food._id\": 0,\n" +
                    "  \t\t\"food._class\": 0,\n" +
                    "    }\n" +
                    "  }"
    })
    List<foodRecommendation> foodRecommendation();



    @Aggregation(pipeline = {
            "{$match : { user_id : ?0 }}",
            "{$group : {_id : \"$createdAt\", totalGainedCalories : {$sum : \"$totalGainedCalories\"}}}",
            "{$project : { _id : 0, date : \"$_id\" , calories : \"$totalGainedCalories\"}}"
    })
    List<ChartItem> getChart(String userId);
}
