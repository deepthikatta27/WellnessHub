package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.UserFoodTracking;
import com.project.HealthTrackManagement.Repository.IUserFoodTrackingRepository;
import com.project.HealthTrackManagement.Service.IUserFoodTrackingService;
import com.project.HealthTrackManagement.dao.ChartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserFoodTrackingService implements IUserFoodTrackingService
{
    @Autowired
    private IUserFoodTrackingRepository userFoodTrackingRepository;

    @Override
    public UserFoodTracking addUserFoodTracking(UserFoodTracking userFoodTracking) {
        return userFoodTrackingRepository.save(userFoodTracking);
    }

    @Override
    public List<UserFoodTracking> getAllUserFoodTracking() {
        return userFoodTrackingRepository.findAll();
    }

    @Override
    public UserFoodTracking getUserFoodTrackingById(String id) {
        return userFoodTrackingRepository.findById(id).get();
    }

    @Override
    public UserFoodTracking updateUserFoodTracking(UserFoodTracking userFoodTracking) {
        return userFoodTrackingRepository.save(userFoodTracking);
    }

    @Override
    public void deleteUserFoodTrackingById(String id)
    {
        userFoodTrackingRepository.deleteById(id);
    }

    @Override
    public List<ChartItem> getChart(String userId) {
        return userFoodTrackingRepository.getChart(userId);
    }
}


