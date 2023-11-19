package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.UserExerciseTracking;
import com.project.HealthTrackManagement.Repository.IUserExerciseTrackingRepository;
import com.project.HealthTrackManagement.Service.IUserExerciseTrackingService;
import com.project.HealthTrackManagement.dao.ChartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserExerciseTrackingService implements IUserExerciseTrackingService
{
    @Autowired
    private IUserExerciseTrackingRepository userExerciseTrackingRepository;

    @Override
    public UserExerciseTracking addUserExerciseTracking(UserExerciseTracking userExerciseTracking) {
        return userExerciseTrackingRepository.save(userExerciseTracking);
    }

    @Override
    public List<UserExerciseTracking> getAllUserExerciseTracking() {
        return userExerciseTrackingRepository.findAll();
    }

    @Override
    public UserExerciseTracking getUserExerciseTrackingById(String id) {
        return userExerciseTrackingRepository.findById(id).get();
    }

    @Override
    public UserExerciseTracking updateUserExerciseTracking(UserExerciseTracking userExerciseTracking) {
        return userExerciseTrackingRepository.save(userExerciseTracking);
    }

    @Override
    public void deleteUserExerciseTrackingById(String id) {
        userExerciseTrackingRepository.deleteById(id);
    }

    @Override
    public List<ChartItem> getChart(String userId) {
        return userExerciseTrackingRepository.getChart(userId);
    }
}
