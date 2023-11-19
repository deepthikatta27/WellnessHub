package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.UserExerciseTracking;
import com.project.HealthTrackManagement.dao.ChartItem;

import java.util.List;

public interface IUserExerciseTrackingService
{
    UserExerciseTracking addUserExerciseTracking(UserExerciseTracking userExerciseTracking);

    List<UserExerciseTracking> getAllUserExerciseTracking();

    UserExerciseTracking getUserExerciseTrackingById(String id);

    UserExerciseTracking updateUserExerciseTracking(UserExerciseTracking userExerciseTracking);

    void deleteUserExerciseTrackingById(String id);

    List<ChartItem> getChart(String userId);
}
