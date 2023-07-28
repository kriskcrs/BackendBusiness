package com.business.backendBusiness.service;

import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
                            if (workItem.getEndTime() == null) {
                                System.out.println("se cumple");
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
                                conexionOnline.setSession(historyItem.getIdsession());
                                conexionOnline.setIdHistorySession(historyItem.getIdhistorySession());
                                conexionOnline.setIdEmployee(workItem.getEmployeeIdemployee());
                                conexionList.add(conexionOnline);
                            }
                        }
                    }
                }
            }
        }
        return conexionList;
    }


    @GetMapping(path = "/check-online/{sesion}/{user}")
    private int checkOnline(@PathVariable("sesion") String sesion, @PathVariable("user") String user) {
        try {
            System.out.println("se valida si existe la sesion -> " + sesion + " --> " + user);
            User userFind = userRepository.findByUser(user);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(sesion, userFind.getIduser());
            if (historySession.isPresent()) {
                System.out.println("Sesion existe" + sesion);
                return 0;
            }
            System.out.println("Sesion no encontrada" + sesion);
            return 1;
        } catch (Exception e) {
            System.out.println("Error Validations -> " + e.getMessage() + "\nError  Validations  Cause -> " + e.getCause());
            return 1;
        }
    }

    @GetMapping(path = "/logout/{sesion}/{user}")
    private int logoutUser(@PathVariable("sesion") String sesion, @PathVariable("user") String user) {
        try {
            System.out.println("se manda a finalizar -> " + sesion + " --> " + user);
            User userFind = userRepository.findByUser(user);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(sesion, userFind.getIduser());
            if (historySession.isPresent()) {
                System.out.println("se finaliza" + historySession.get().getIdsession());

                List<Work> workList = workRepository.findByEmployeeIdemployee(userFind.getEmployeeIdemployee());
                int le = workList.size();

                for (int i = 0; i < le; i++) {
                    if (workList.get(i).getEndTime() == null) {
                        Work worFin = new Work();
                        worFin.setIdwork(workList.get(i).getIdwork());
                        worFin.setStartTime(workList.get(i).getStartTime());
                        worFin.setEndTime(new Date());
                        //calcula tiempo
                        worFin.setTotalHour(calculateHours(worFin.getEndTime(), workList.get(i).getStartTime()));
                        worFin.setDateWork(workList.get(i).getDateWork());
                        worFin.setRateToday(workList.get(i).getRateToday());
                        worFin.setEndGeo(workList.get(i).getStartGeo());
                        worFin.setStartGeo(workList.get(i).getStartGeo());
                        worFin.setEmployeeIdemployee(workList.get(i).getEmployeeIdemployee());
                        workRepository.save(worFin);
                    }
                }
                historySessionRepository.deleteById(historySession.get().getIdhistorySession());
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println("Error Validations -> " + e.getMessage() + "\nError  Validations  Cause -> " + e.getCause());
            return 1;
        }
    }


    private int calculateHours(Date start, Date end) {
        long diferenciaMilisegundos =   start.getTime()-end.getTime();
        return (int) (diferenciaMilisegundos / (1000 * 60 ));
    }



}
