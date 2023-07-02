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
    private Person createPerson(@RequestBody Person person) {
        try {
            int id = personRepository.findAll().size();
            id++;
            person.setIdperson((long) id);
            personRepository.save(person);
            //create user
            User user = new User();
            user.setUser(person.getFirstName().substring(0, 1) + person.getLastName() + id);
            user.setPassword(new md5().encode("1"));
            user.setPersonIdperson(person.getIdperson());
            createUser(user);
            return person;

        } catch (Exception e) {
            System.out.println("Error CreatePerson -> " + e.getMessage());
            System.out.println("Error Cause CreatePerson -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/updateUser")
    private User createUser(@RequestBody User user) {
        try {
            int id = userRepository.findAll().size();
            id++;
            user.setIduser((long) id);
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error CreateUser -> " + e.getMessage());
            System.out.println("Error Cause CreateUser -> " + e.getCause());
            return null;
        }
    }




    @PostMapping(path="/location")
    private Location createLocation(@RequestBody Location location){
       try{
           return locationRepository.save(location);
       }catch (Exception e){
           System.out.println("Error creatLocation -> " + e.getMessage());
           return null;
       }

    }

    @PostMapping(path="/state")
    private State createState(@RequestBody State state){
        return stateRepository.save(state);
    }

    @PostMapping(path="/employee")
    private Employee createState(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PostMapping(path="/work")
    private Work createState(@RequestBody Work work){
        return workRepository.save(work);
    }



}
