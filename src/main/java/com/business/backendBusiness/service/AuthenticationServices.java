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
    EmployeeRepository employeeRepository;
    @Autowired
    WorkRepository workRepository;
    boolean firstLogin = false;

    @PostMapping(path = "/authentications")
    private LoginData login(@RequestBody LoginData loginData) {
        try {
            if (loginData.getUser().getPassword().equals("1")) {
                firstLogin = true;
            }
            //setter user and password
            loginData.getUser().setUser((loginData.getUser().getUser()).toUpperCase());
            loginData.getUser().setPassword(new EncodeUUID().encode(loginData.getUser().getPassword()));
            //find user
            User userFind = userRepository.findByUserAndPassword(loginData.getUser().getUser(), loginData.getUser().getPassword());
            loginData.setUser(userFind);
            if (userFind != null && !firstLogin) {
                //registry history
                System.out.println("first login -> " + firstLogin);
                return registryUser(loginData, userFind);
            } else {
                System.out.println("first login -> " + firstLogin);
                assert userFind != null;
                LoginData loginDataTemp = registryUser(loginData, userFind);
                loginDataTemp.setMessage("1");
                return loginDataTemp;
            }
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        loginData.setHistorySession(null);
        loginData.setMessage("failed login");
        return loginData;
    }

    public LoginData registryUser(LoginData loginData, User userFind) {
        long id = historySessionRepository.findAll().size();
        id++;
        loginData.getHistorySession().setIdhistorySession(id);
        loginData.getHistorySession().setIdsession(String.valueOf(new EncodeUUID().SessionManager()));
        loginData.getHistorySession().setUserIduser(userFind.getIduser());
        loginData.getHistorySession().setStateIdstate(1L);
        loginData.setHistorySession(loginData.getHistorySession());
        historySessionRepository.save(loginData.getHistorySession());
        //complete
        loginData.setMessage("successful");
        loginData.getUser().setPassword(null);
        return loginData;
    }


    @PostMapping(path = "/revoke")
    private String logout(@RequestBody LoginData loginData) {
        try {
            System.out.println("delete -> " + loginData.getHistorySession().getIdhistorySession());
            User user = userRepository.findByUser(loginData.getUser().getUser());
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(loginData.getHistorySession().getIdsession(), user.getIduser());
            historySessionRepository.deleteById(historySession.get().getIdhistorySession());
            return "Delete OK";
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
            return "No present data";
        }
    }


    @PostMapping("/registryWork")
    public String registryWork(@RequestBody CreateData createData) {
        try {
            System.out.println("work record IdEmployee -> " + createData.getEmployee().getIdemployee());
            if (new KeepAlive().validationSession(createData)) {
                long id = workRepository.findAll().size();
                id++;
                Work work = new Work();
                work.setIdwork(id);
                work.setDateWork(new Date());
                Optional<Employee> employeeData = employeeRepository.findById(createData.getEmployee().getIdemployee());
                work.setRateToday(employeeData.get().getRate());
                work.setStartTime(new Date());
                work.setEmployeeIdemployee(createData.getEmployee().getIdemployee());
                workRepository.save(work);
                return "Ok";
            }
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        return "Error";
    }

    @PostMapping("/registryWorkFinish")
    public String unregistryWork(@RequestBody CreateData createData) {
        try {
            if (new KeepAlive().validationSession(createData)) {
                System.out.println("work record IdEmployee -> " + createData.getEmployee().getIdemployee());
                long id = workRepository.findAll().size();
                id++;
                Work work = new Work();
                work.setIdwork(id);
                work.setDateWork(new Date());
                Optional<Employee> employeeData = employeeRepository.findById(createData.getEmployee().getIdemployee());
                work.setRateToday(employeeData.get().getRate());
                work.setStartTime(new Date());
                work.setEmployeeIdemployee(createData.getEmployee().getIdemployee());
                workRepository.save(work);
                return "Ok";
            }

        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        return "Error";
    }

}