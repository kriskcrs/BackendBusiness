package com.business.backendBusiness.service;

import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("consult")
@CrossOrigin
public class dataService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    WorkRepository workRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/person")
    private List<Person> personList() {
        return personRepository.findAll();
    }

    @GetMapping(path = "/employee")
    private List<Employee> employeeList() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/location")
    private List<Location> locationList() {
        return locationRepository.findAll();
    }

    @GetMapping(path = "/state")
    private List<State> stateList() {
        return stateRepository.findAll();
    }

    @GetMapping(path = "/work")
    private List<Work> workList() {
        return workRepository.findAll();
    }

    @GetMapping(path = "/user")
    private List<User> userList() {
        return userRepository.findAll();
    }

}
