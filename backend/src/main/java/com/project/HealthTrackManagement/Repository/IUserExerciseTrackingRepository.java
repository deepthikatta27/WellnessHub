package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.UserExerciseTracking;
import com.project.HealthTrackManagement.dao.ChartItem;
import com.project.HealthTrackManagement.dao.Recommendation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserExerciseTrackingRepository extends MongoRepository<UserExerciseTracking, String> {


    @Aggregation(pipeline = {
            "{\n" +
                    "    $addFields: {\n" +
                    "      excercise_id_test: {\n" +
                    "        $toObjectId: \"$exercise_id\",\n" +
                    "      },\n" +
                    "      user_id_test: {\n" +
                    "        $toObjectId: \"$user_id\",\n" +
                    "      },\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"exerciseRecommendation\",\n" +
                    "      localField: \"excercise_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"excercise\",\n" +
                    "    },\n" +
                    "  }",
            "{\n" +
                    "    $lookup: {\n" +
                    "      from: \"user\",\n" +
                    "      localField: \"user_id_test\",\n" +
                    "      foreignField: \"_id\",\n" +
                    "      as: \"user\",\n" +
                    "    },\n" +
                    "  },",
            "{\n" +
                    "    $project: {\n" +
                    "      totalLooseCalories: 1,\n" +
                    "      exercise_period: 1,\n" +
                    "      createdAt: 1,\n" +
                    "      excercise: {\n" +
                    "        $arrayElemAt: [\"$excercise\", 0],\n" +
                    "      },\n" +
                    "      user: { $arrayElemAt: [\"$user\", 0] },\n" +
                    "    },\n" +
                    "  },",
            "{\n" +
                    "    $project: {\n" +
                    "      \"user._id\": 0,\n" +
                    "      \"user._class\": 0,\n" +
                    "      \"excercise._id\": 0,\n" +
                    "      \"excercise._class\": 0,\n" +
                    "    },\n" +
                    "  },"
    })
    List<Recommendation> getRecommendations();


    @Aggregation(pipeline = {
            "{$match : { user_id : ?0 }}",
            "{$group : {_id : \"$createdAt\", totalCaloriesBurned : {$sum : \"$totalLooseCalories\"}}},",
            "{$project : { _id : 0, date : \"$_id\" , calories : \"$totalCaloriesBurned\"}},"
    })
    List<ChartItem> getChart(String userId);
}
