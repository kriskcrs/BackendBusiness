package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("create")
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

    @PostMapping(path = "/person")
    private Object createPerson(@RequestBody Person person) {
        try {
            int id = personRepository.findAll().size();
            id++;
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
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error updateUser -> " + e.getMessage());
            System.out.println("Error Cause updateUser -> " + e.getCause());
            return null;
        }
    }



}
