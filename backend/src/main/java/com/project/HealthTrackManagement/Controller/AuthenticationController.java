package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Model.User;
import com.project.HealthTrackManagement.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController
{
    @Autowired
    private UserService userService;

    @PostMapping("/userLogin")
    public ResponseEntity<?> studentLogin(@RequestBody HashMap<String, String> login) {
        try {
            String email = login.get("email");
            String password = login.get("password");

            if (email == null || password == null) {
                return new ResponseEntity<>("Email and password fields are required", HttpStatus.BAD_REQUEST);
            }

            User user = userService.findByEmailAndPassword(email,password);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }

            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>("An error occurred during student login: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
