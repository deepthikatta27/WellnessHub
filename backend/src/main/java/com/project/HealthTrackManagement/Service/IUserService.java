package com.project.HealthTrackManagement.Service;

import com.project.HealthTrackManagement.Model.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    List<User> getAllUsers();

    User getUSerById(String id);

    User updateUser(User user);

    void deleteUser(String id);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
