package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
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
    @Autowired
    RolRepository rolRepository;

    @PostMapping(path = "/person")
    private Object createPerson(@RequestBody Person person) {
        try {
            id = personRepository.findAll().size();
            id++;
            person.setIdperson((long) id);
            personRepository.save(person);
            Employee employee = new Employee();
            employee.setPersonIdperson(person.getIdperson());
            return createEmployee(employee);
        } catch (Exception e) {
            System.out.println("Error CreatePerson -> " + e.getMessage());
            System.out.println("Error Cause CreatePerson -> " + e.getCause());
            return null;
        }
    }

    private User createEmployee(@RequestBody Employee employee) {
        try {
            //create employee
            id = employeeRepository.findAll().size();
            id++;
            employee.setIdemployee((long) id);
            employee.setHireDate(new Date(2023, Calendar.JULY, 1));
            employee.setDobDate(null);
            employee.setRate(15.54);
            Optional<Person> personData = personRepository.findById(employee.getPersonIdperson());
            employee.setLocationIdlocation((long) 1);
            employee.setStateIdstate((long) 1);
            employeeRepository.save(employee);
            //create user
            User userData = new User();
            int idUser = userRepository.findAll().size();
            idUser++;
            userData.setIduser((long) idUser);
            userData.setRolIdrol((long) 1);
            userData.setUser(personData.get().getFirstName().charAt(0) + personData.get().getLastName() + id);
            userData.setPassword(new md5().encode("1"));
            userData.setEmployeeIdemployee(employee.getPersonIdperson());
            userRepository.save(userData);
            return userData;
        } catch (Exception e) {
            System.out.println("Error createEmployee -> " + e.getMessage());
            System.out.println("Error Cause createEmployee -> " + e.getCause());
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

    @PostMapping(path = "/employeeUpdate")
    private Employee updateEmployee(@RequestBody Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            System.out.println("Error updateUser -> " + e.getMessage());
            System.out.println("Error Cause updateUser -> " + e.getCause());
            return null;
        }
    }

    @PostMapping(path = "/rolUpdate")
    private Rol updateRol(@RequestBody Rol rol) {
        try {
            return rolRepository.save(rol);
        } catch (Exception e) {
            System.out.println("Error updateUser -> " + e.getMessage());
            System.out.println("Error Cause updateUser -> " + e.getCause());
            return null;
        }
    }

}
