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
    public int id;
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

    @PostMapping(path = "/person")
    private Object createPerson(@RequestBody Person person) {
        try {
            id = personRepository.findAll().size();id++;
            person.setIdperson((long) id);
            personRepository.save(person);
            //create user
            User userData = new User();
            userData.setUser(person.getFirstName().charAt(0) + person.getLastName() + id);
            userData.setPassword(new md5().encode("1"));
            userData.setPersonIdperson(person.getIdperson());
            int idUser = userRepository.findAll().size();
            idUser++;
            userData.setIduser((long) idUser);
            System.out.println("uuid -> " + new md5().SessionManager());
            userRepository.save(userData);
            return userData;
        } catch (Exception e) {
            System.out.println("Error CreatePerson -> " + e.getMessage());
            System.out.println("Error Cause CreatePerson -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/location")
    private Location createLocation(@RequestBody Location location) {
        try {
            id = locationRepository.findAll().size();id++;
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
            id = stateRepository.findAll().size();id++;
            state.setIdstate((long) id);
            return stateRepository.save(state);
        } catch (Exception e) {
            System.out.println("Error createState -> " + e.getMessage());
            System.out.println("Error Cause createState -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/employee")
    private Employee createState(@RequestBody Employee employee) {
        try {
            id = employeeRepository.findAll().size();id++;
            employee.setIdemployee((long) id);
            return employeeRepository.save(employee);
        } catch (Exception e) {
            System.out.println("Error createEmployee -> " + e.getMessage());
            System.out.println("Error Cause createEmployee -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/work")
    private Work createState(@RequestBody Work work) {
        try {
            id = workRepository.findAll().size();id++;
            work.setIdwork((long) id);
            return workRepository.save(work);
        } catch (Exception e) {
            System.out.println("Error createWork -> " + e.getMessage());
            System.out.println("Error Cause createWork -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/userUpdate")
    private User updateUser(@RequestBody User user) {
        try {
            id = userRepository.findAll().size();id++;
            user.setIduser((long) id);
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error updateUser -> " + e.getMessage());
            System.out.println("Error Cause updateUser -> " + e.getCause());
            return null;
        }
    }


}
