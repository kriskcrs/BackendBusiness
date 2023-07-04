package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.UserRepository;
import com.business.backendBusiness.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationServices {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/authentications")
    private Optional<User> login(@RequestBody User user) {
        try {
            System.out.println("login -> " + user.getUser());
            user.setPassword(new md5().encode(user.getPassword()));
            return userRepository.findByUserAndPassword(user.getUser(), user.getPassword());
        } catch (Exception e) {
            System.out.println("Message error -> " + e.getMessage());
            System.out.println("Message Cause -> " + e.getCause());
            return Optional.ofNullable(user);
        }

    }
}
