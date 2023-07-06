package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
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
    @Autowired
    WorkRepository workRepository;
    @Autowired
    RolRepository rolRepository;


    @PostMapping(path = "/authentications")
    private LoginData login(@RequestBody User user) {
        try {
            System.out.println("login -> " + user.getUser());
            user.setUser((user.getUser()).toUpperCase());
            user.setPassword(new md5().encode(user.getPassword()));
            Optional<User> userFind = userRepository.findByUserAndPassword(user.getUser(), user.getPassword());


           //registra historial
            id = historySessionRepository.findAll().size();
            id++;
            HistorySession historySession = new HistorySession();
            historySession.setIdhistorySession((long) id);
            historySession.setUserIduser(user.getIduser());
            registry(userFind, historySession);
            //registra trabajo
            Optional<Employee> employee = employeeRepository.findById(userFind.get().getEmployeeIdemployee());
            registryWork(employee);


            HistorySession historySessionData = new HistorySession();
            Optional<HistorySession> history = historySessionRepository.findById(userFind.get().getIduser());
            historySessionData.setIdsession(history.get().getIdsession());

            return new LoginData(userFind,history);

        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
            return null;
        }
    }


    private void registry(Optional<User> user, HistorySession historySession) {
        try {
            System.out.println("registra historico " + user.isPresent());
            historySession.setIdsession(String.valueOf(new md5().SessionManager()));
            historySession.setStateIdstate(1L);
            historySession.setUserIduser(user.get().getIduser());
            historySessionRepository.save(historySession);
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
    }


    private void registryWork(Optional<Employee> employee) {
        try {
            System.out.println("registra Trabajo -> " + employee.isPresent());
            id = workRepository.findAll().size();
            id++;
            Work work = new Work();
            work.setIdwork((long) id);
            work.setEmployeeIdemployee(employee.get().getIdemployee());
            Date date = new Date();
            work.setStartTime(date);
            work.setDateWork(date);
            work.setRateToday(0.00);
            work.setStateIdstate((long)1);
            work.setTotalHour(0);
            workRepository.save(work);
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
    }




}