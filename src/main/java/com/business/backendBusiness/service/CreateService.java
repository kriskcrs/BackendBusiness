package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("v2")
@CrossOrigin
public class CreateService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    HistorySessionRepository historySessionRepository;

    public long id;
    public long idP;
    public long idE;
    public long idU;
    public boolean sessionValid = false;

    @PostMapping(path = "/person")
    private CreateData createPersonEmployeeUser(@RequestBody CreateData createData) {
        try {
            if (validationSession(createData)) {
                //create person
                idP = personRepository.findAll().size();
                idP++;
                createData.getPerson().setIdperson(idP);
                createData.setPerson(createData.getPerson());
                personRepository.save(createData.getPerson());

                //create Employee
                idE = employeeRepository.findAll().size();
                idE++;
                createData.getEmployee().setIdemployee(idE);
                createData.getEmployee().setPersonIdperson(idP);
                createData.setEmployee(createData.getEmployee());
                employeeRepository.save(createData.getEmployee());

                //create user
                idU = userRepository.findAll().size();
                idU++;
                createData.getUser().setIduser(idU);
                createData.getUser().setPassword(new EncodeUUID().encode(createData.getUser().getPassword()));
                createData.getUser().setUser((createData.getPerson().getFirstName().charAt(0) + createData.getPerson().getLastName() + idP).toUpperCase());
                createData.getUser().setEmployeeIdemployee(idE);
                createData.setUser(createData.getUser());
                userRepository.save(createData.getUser());
                return createData;
            }

        } catch (Exception e) {
            System.out.println("Error CreatePerson -> " + e.getMessage());
            System.out.println("Error Cause CreatePerson -> " + e.getCause());
            return null;
        }
        createData = null;
        return createData;
    }


    @PostMapping(path = "/work")
    private Work createWork(@RequestBody CreateData createData) {
        try {
            if (validationSession(createData)) {
                id = workRepository.findAll().size();
                id++;
                createData.getWork().setIdwork(id);
                return workRepository.save(createData.getWork());
            }
        } catch (Exception e) {
            System.out.println("Error createWork -> " + e.getMessage());
            System.out.println("Error Cause createWork -> " + e.getCause());
        }
        return null;
    }

    @PostMapping(path = "/location")
    private Location createLocation(@RequestBody CreateData createData) {
        try {
            if (validationSession(createData)) {
                id = locationRepository.findAll().size();
                id++;
                createData.getLocation().setIdlocation(id);
                return locationRepository.save(createData.getLocation());
            }
        } catch (Exception e) {
            System.out.println("Error createLocation -> " + e.getMessage());
            System.out.println("Error Cause createLocation -> " + e.getCause());
        }
        return null;
    }

    @PostMapping(path = "/state")
    private State createState(@RequestBody CreateData createData) {
        try {
            if (validationSession(createData)) {
                id = stateRepository.findAll().size();
                id++;
                createData.getState().setIdstate(id);
                return stateRepository.save(createData.getState());
            }
        } catch (Exception e) {
            System.out.println("Error createState -> " + e.getMessage());
            System.out.println("Error Cause createState -> " + e.getCause());

        }
        return null;
    }

    public boolean validationSession(CreateData createData) {
        try {
            long iduser = createData.getUser().getIduser();
            String idsession = createData.getHistorySession().getIdsession();
            System.out.println("user id -> " + iduser + "\nsession -> " + idsession);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(idsession, iduser);
            System.out.println(historySession);
            if (historySession.isPresent()) {
                if (historySession.get().getStateIdstate() == 1) {
                    return sessionValid = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Validation -> " + e.getCause());
        }
        return sessionValid = false;
    }


}
