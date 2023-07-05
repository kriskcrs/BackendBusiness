package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.HistorySessionRepository;
import com.business.backendBusiness.Repository.UserRepository;
import com.business.backendBusiness.entity.HistorySession;
import com.business.backendBusiness.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/v1")
@CrossOrigin
public class AuthenticationServices {

    private int id;

    @Autowired
    UserRepository userRepository;
    @Autowired
    HistorySessionRepository historySessionRepository;

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


    private void registry(User user){
        try{
            id = historySessionRepository.findAll().size();id++;
            HistorySession historySession = new HistorySession();
            historySession.setIdhistorySession((long) id);
            historySession.setUserIduser(user.getIduser());

        }catch (Exception e){
            System.out.println("Error -> "+e.getMessage()+"\nError causa -> "+e.getCause());
        }

    }

}
