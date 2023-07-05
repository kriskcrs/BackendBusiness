package com.business.backendBusiness.service;

import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v3")
@CrossOrigin
public class ConsultService {


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


    @GetMapping(path = "/employee")
    private List<Employee> employeeList() {
        return employeeRepository.findAll();
    }


    @GetMapping(path = "/location")
    private List<Location> locationList() {
        return locationRepository.findAll();
    }

    @GetMapping(path = "/location/{id}")
    private Optional<Location> location(@PathVariable("id") Integer id) {
        return locationRepository.findById(Long.valueOf(id));
    }


    @GetMapping(path = "/person")
    private List<Person> personList() {
        return personRepository.findAll();
    }

    @GetMapping(path = "/person/{name}/{lastName}")
    private List<Person> personName(@PathVariable("name") String name, @PathVariable("lastName") String last) {
        return personRepository.findByFirstNameOrLastName(name, last);
    }

    @GetMapping(path = "/person/{id}")
    private Optional<Person> personId(@PathVariable("id") Integer id) {
        return personRepository.findById(Long.valueOf(id));
    }


    @GetMapping(path = "/state")
    private List<State> stateList() {
        return stateRepository.findAll();
    }

    @GetMapping(path = "/rol")
    private List<Rol> rol() {
        return rolRepository.findAll();
    }


    @GetMapping(path = "/user")
    private List<User> userList() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/work")
    private List<Work> workList() {
        return workRepository.findAll();
    }


}
