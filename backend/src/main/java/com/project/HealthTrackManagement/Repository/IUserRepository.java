package com.project.HealthTrackManagement.Repository;

import com.project.HealthTrackManagement.Model.User;
import com.project.HealthTrackManagement.dao.Recommendation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends MongoRepository<User,String>
{
    User findByEmailAndPassword(String Email, String password);


    User findByEmail(String email);
}
