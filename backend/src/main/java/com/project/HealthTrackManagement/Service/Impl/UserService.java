package com.project.HealthTrackManagement.Service.Impl;

import com.project.HealthTrackManagement.Model.User;
import com.project.HealthTrackManagement.Repository.IUserRepository;
import com.project.HealthTrackManagement.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUSerById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
