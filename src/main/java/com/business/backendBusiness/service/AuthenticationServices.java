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


    @PostMapping(path = "/authentications")
    private LoginData login(@RequestBody LoginData loginData) {
        try {
            boolean firstLogin = false;
            System.out.println("login " + loginData.getUser().getUser());
            if (loginData.getUser().getPassword().equals("1")) {
                firstLogin = true;
            }
            //setter user and password
            loginData.getUser().setUser((loginData.getUser().getUser()).toUpperCase());
            loginData.getUser().setPassword(new EncodeUUID().encode(loginData.getUser().getPassword()));
            //find user
            User userFind = userRepository.findByUserAndPassword(loginData.getUser().getUser(), loginData.getUser().getPassword());
            loginData.setUser(userFind);

            HistorySession historySessionFind = historySessionRepository.findByUserIduser(userFind.getIduser());

            if (historySessionFind == null) {
                if (!firstLogin) {
                    //registry history
                    return registryUser(loginData, userFind);
                } else {
                    loginData = registryUser(loginData, userFind);
                    loginData.setMessage("1");
                    return loginData;
                }
            }
            loginData.setUser(null);
            loginData.setHistorySession(null);
            loginData.setMessage("1 already connected");
            return loginData;
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        loginData.setUser(null);
        loginData.setHistorySession(null);
        loginData.setMessage("2 failed login");
        return loginData;
    }

    private LoginData registryUser(LoginData loginData, User userFind) {
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
            return "200";
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
            return "No present data";
        }
    }


    @PostMapping("/registryWork")
    private Work registryWork(@RequestBody CreateData createData) {
        try {
            System.out.println("work record IdEmployee -> " + createData.getEmployee().getIdemployee());
            System.out.println(createData.getWork().getStartGeo());
            if (validationSession(createData)) {
                long id = workRepository.findAll().size();
                id++;
                Work work = new Work();
                work.setIdwork(id);
                work.setDateWork(new Date());
                Optional<Employee> employeeData = employeeRepository.findById(createData.getEmployee().getIdemployee());
                work.setRateToday(employeeData.get().getRate());
                work.setStartTime(new Date());
                work.setEmployeeIdemployee(createData.getEmployee().getIdemployee());
                work.setTotalHour(createData.getWork().getTotalHour());
                work.setStartGeo(createData.getWork().getStartGeo());

                return workRepository.save(work);
            }
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        return null;
    }

    @PostMapping("/registryWorkFinish")
    private Work unregistryWork(@RequestBody CreateData createData) {
        try {
            if (validationSession(createData)) {
                System.out.println("work record IdEmployee -> " + createData.getEmployee().getIdemployee());
                Optional<Work> work = workRepository.findById(createData.getWork().getIdwork());
                work.get().setEndTime(new Date());
                Optional<Employee> employeeData = employeeRepository.findById(createData.getEmployee().getIdemployee());
                work.get().setRateToday(employeeData.get().getRate());
                work.get().setEmployeeIdemployee(createData.getEmployee().getIdemployee());
                work.get().setEndGeo(createData.getWork().getEndGeo());
                long diferenciaMilisegundos = work.get().getEndTime().getTime() - work.get().getStartTime().getTime();
                int horas = (int) (diferenciaMilisegundos / (1000 * 60 * 60));
                work.get().setTotalHour(horas);

                Work wordNew = work.get();

                return workRepository.save(wordNew);
            }

        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError causa -> " + e.getCause());
        }
        return null;
    }


    private boolean validationSession(CreateData createData) {
        try {
            Long user = createData.getHistorySession().getUserIduser();
            String idsession = createData.getHistorySession().getIdsession();
            System.out.println("user id -> " + user + "\nsession -> " + idsession);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(idsession, user);
            System.out.println("sesion encontrada -> " + historySession);
            if (historySession.isPresent()) {
                if (historySession.get().getStateIdstate() == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Validations -> " + e.getMessage() + "\nError  Validations  Cause -> " + e.getCause());
            return false;
        }
        return false;
    }

}