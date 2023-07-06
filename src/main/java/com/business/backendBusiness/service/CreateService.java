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
    public long id;
    public long idP;
    public long idE;
    public long idU;
    public boolean sessionValid = false;
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
    RolRepository rolRepository;
    @Autowired
    HistorySessionRepository historySessionRepository;


    @PostMapping(path = "/person")
    private CreateData createPersonEmployeeUser(@RequestBody CreateData createData) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error CreatePerson -> " + e.getMessage());
            System.out.println("Error Cause CreatePerson -> " + e.getCause());
            return null;
        }
    }


    @PostMapping(path = "/work")
    private Work createWork(@RequestBody Work work) {
        try {
            id = workRepository.findAll().size();
            id++;
            work.setIdwork((long) id);
            return workRepository.save(work);
        } catch (Exception e) {
            System.out.println("Error createWork -> " + e.getMessage());
            System.out.println("Error Cause createWork -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/location")
    private Location createLocation(@RequestBody Location location) {
        try {
            id = locationRepository.findAll().size();
            id++;
            location.setIdlocation((long) id);
            return locationRepository.save(location);
        } catch (Exception e) {
            System.out.println("Error createLocation -> " + e.getMessage());
            System.out.println("Error Cause createLocation -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/state")
    private State createState(@RequestBody State state) {
        try {
            id = stateRepository.findAll().size();
            id++;
            state.setIdstate((long) id);
            return stateRepository.save(state);
        } catch (Exception e) {
            System.out.println("Error createState -> " + e.getMessage());
            System.out.println("Error Cause createState -> " + e.getCause());
            return null;
        }
    }



    public void validationSession(String idSession, Long user) {
        try {
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(idSession, user);
            sessionValid = true;
            System.out.println("encontrado");
        } catch (Exception e) {
            System.out.println("fallo");
        }
    }


}
