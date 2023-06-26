package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.UserRepository;
import com.business.backendBusiness.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@CrossOrigin
public class AuthenticationServices {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/authentications")
    private Optional<User> login(@RequestBody User user) {

        try{

            System.out.println("Data User -> " + user.getIduser());
            System.out.println("Data password -> " + user.getPassword());
            user.setPassword(new md5().encode(user.getPassword()));
             return userRepository.findByAndIduserAndPassword(user.getIduser(),user.getPassword());
        }catch (Exception e){
            System.out.println("Message error -> "+e.getMessage());
            System.out.println("Message Cause -> "+e.getCause());
            return null;
        }

    }
}
