package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.UserFoodTracking;
import com.project.HealthTrackManagement.dao.ChartItem;

import java.util.List;

public interface IUserFoodTrackingService
{
    UserFoodTracking addUserFoodTracking(UserFoodTracking userFoodTracking);

    List<UserFoodTracking> getAllUserFoodTracking();

    UserFoodTracking getUserFoodTrackingById(String id);

    UserFoodTracking updateUserFoodTracking(UserFoodTracking userFoodTracking);

    void deleteUserFoodTrackingById(String id);

    List<ChartItem> getChart(String userId);
}
