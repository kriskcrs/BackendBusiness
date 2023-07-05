package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.EmployeeRepository;
import com.business.backendBusiness.Repository.HistorySessionRepository;
import com.business.backendBusiness.Repository.StateRepository;
import com.business.backendBusiness.Repository.UserRepository;
import com.business.backendBusiness.entity.Employee;
import com.business.backendBusiness.entity.HistorySession;
import com.business.backendBusiness.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    StateRepository stateRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(path = "/authentications")
    private Optional<User> login(@RequestBody User user) {
        try {
            System.out.println("login -> " + user.getUser());
            user.setPassword(new md5().encode(user.getPassword()));

            id = historySessionRepository.findAll().size();
            id++;
            HistorySession historySession = new HistorySession();
            historySession.setIdhistorySession((long) id);
            registry(user, historySession);
            Optional<Employee> employee = employeeRepository.findById(user.getEmployeeIdemployee());
            registryWork(employee);
            return userRepository.findByUserAndPassword(user.getUser(), user.getPassword());
        } catch (Exception e) {
            System.out.println("Message error -> " + e.getMessage());
            System.out.println("Message Cause -> " + e.getCause());
            return Optional.ofNullable(user);
        }
    }


    private void registry(User user, HistorySession historySession) {
        try {
            historySession.setUserIduser(user.getIduser());
            historySession.setIdsession(String.valueOf(new md5().getSessionId()));
            historySession.setStateIdstate(1L);
            historySessionRepository.save(historySession);
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
    }


    private void registryWork(Optional<Employee> employee) {
        try {



        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
    }




}