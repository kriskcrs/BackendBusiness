package com.business.backendBusiness.service;

import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    HistorySessionRepository historySessionRepository;


    @GetMapping(path = "/employee")
    private List<Employee> employeeList() {
        System.out.println("consumo del servicio -> employeeList");

        return employeeRepository.findAll();
    }


    @GetMapping(path = "/employee/{id}")
    private Optional<Employee> employee(@PathVariable("id") Integer id) {
        System.out.println("consumo del servicio -> employee" + id);
        return employeeRepository.findById(Long.valueOf(id));
    }

    @GetMapping(path = "/location")
    private List<Location> locationList() {
        System.out.println("consumo del servicio -> locationList");
        return locationRepository.findAll();
    }

    @GetMapping(path = "/location/{id}")
    private Optional<Location> location(@PathVariable("id") Integer id) {
        System.out.println("consumo del servicio -> locations" + id);
        return locationRepository.findById(Long.valueOf(id));
    }


    @GetMapping(path = "/person")
    private List<Person> personList() {
        System.out.println("consumo del servicio -> personList");
        return personRepository.findAll();
    }

    @GetMapping(path = "/person/{name}/{lastName}")
    private List<Person> personName(@PathVariable("name") String name, @PathVariable("lastName") String last) {
        System.out.println("consumo del servicio -> personName");
        return personRepository.findByFirstNameOrLastName(name, last);
    }

    @GetMapping(path = "/person/{id}")
    private Optional<Person> personId(@PathVariable("id") Integer id) {
        System.out.println("consumo del servicio -> personId" + id);
        return personRepository.findById(Long.valueOf(id));
    }


    @GetMapping(path = "/state")
    private List<State> stateList() {
        System.out.println("consumo del servicio -> stateList");
        return stateRepository.findAll();
    }

    @GetMapping(path = "/rol")
    private List<Rol> rol() {
        System.out.println("consumo del servicio -> rolList");
        return rolRepository.findAll();
    }


    @GetMapping(path = "/user")
    private List<User> userList() {
        System.out.println("consumo del servicio -> userList");
        return userRepository.findAll();
    }

    @GetMapping(path = "/work")
    private List<Work> workList() {
        System.out.println("consumo del servicio -> workList");
        return workRepository.findAll();
    }

    @GetMapping(path = "/online")
    private List<ConexionOnline> conexionList() {
        System.out.println("consumo del servicio -> online");
        List<ConexionOnline> conexionList = new ArrayList<>();

        List<Work> work = workRepository.findAll();
        List<User> user = userRepository.findAll();
        List<HistorySession> historySessions = historySessionRepository.findAll();

        for (Work workItem : work) {
            for (User userItem : user) {
                if (workItem.getEmployeeIdemployee().equals(userItem.getEmployeeIdemployee())) {

                    for (HistorySession historyItem : historySessions) {
                        if (userItem.getEmployeeIdemployee().equals(historyItem.getUserIduser())) {
                            ConexionOnline conexionOnline = new ConexionOnline();
                            conexionOnline.setIdWork(workItem.getIdwork());
                            conexionOnline.setDateWork(workItem.getDateWork());
                            conexionOnline.setStartTime(workItem.getStartTime());
                            conexionOnline.setEndTime(workItem.getEndTime());
                            conexionOnline.setTotalHours(workItem.getTotalHour());
                            conexionOnline.setStartGeo(workItem.getStartGeo());
                            conexionOnline.setEndGeo(workItem.getEndGeo());
                            conexionOnline.setRateToDay(workItem.getRateToday());
                            conexionOnline.setUser(userItem.getUser());
                            conexionOnline.setState("Activo");
                            conexionList.add(conexionOnline);
                        }
                    }
                }
            }
        }
        return conexionList;
}


}
