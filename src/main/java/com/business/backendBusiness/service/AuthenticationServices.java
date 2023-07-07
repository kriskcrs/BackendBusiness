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
    private long id;

    @PostMapping(path = "/authentications")
    private LoginData login(@RequestBody LoginData loginData) {
        try {
            System.out.println("login -> " + loginData.getUser().getUser());


            //setter user and password
            loginData.getUser().setUser((loginData.getUser().getUser()).toUpperCase());
            loginData.getUser().setPassword(new EncodeUUID().encode(loginData.getUser().getPassword()));
            //find user
            User userFind = userRepository.findByUserAndPassword(loginData.getUser().getUser(), loginData.getUser().getPassword());
            loginData.setUser(userFind);


            if (userFind != null) {
                //registry history
                System.out.println("registry history -> true");
                id = historySessionRepository.findAll().size();
                id++;
                loginData.getHistorySession().setIdhistorySession(id);
                loginData.getHistorySession().setIdsession(String.valueOf(new EncodeUUID().SessionManager()));
                loginData.getHistorySession().setUserIduser(userFind.getIduser());
                loginData.getHistorySession().setStateIdstate(1L);
                loginData.setHistorySession(loginData.getHistorySession());
                historySessionRepository.save(loginData.getHistorySession());
                //registra trabajo
                Optional<Employee> employee = employeeRepository.findById(userFind.getEmployeeIdemployee());
                System.out.println("work record -> " + employee.isPresent());
                id = workRepository.findAll().size();
                id++;
                Work work = new Work();
                work.setIdwork(id);
                work.setDateWork(new Date());
                work.setRateToday(employee.get().getRate());
                work.setTotalHour(5);
                work.setStartTime(new Date());
                work.setEmployeeIdemployee(employee.get().getIdemployee());
                workRepository.save(work);
                //complete
                loginData.setMessage("successful");
                loginData.getUser().setPassword(null);
                return loginData;
            }

        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        loginData.setHistorySession(null);
        loginData.setMessage("failed login");
        return loginData;
    }

    @PostMapping(path = "/revoke")
    private String logout(@RequestBody LoginData loginData) {
        try {
            System.out.println("delete -> " + loginData.getHistorySession().getIdhistorySession());
            User user = userRepository.findByUser(loginData.getUser().getUser());
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(loginData.getHistorySession().getIdsession(), user.getIduser());
            historySessionRepository.deleteById(historySession.get().getIdhistorySession());
            return "Delete OK" ;
        }catch (Exception e){
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
            return "No present data";
        }

    }

}