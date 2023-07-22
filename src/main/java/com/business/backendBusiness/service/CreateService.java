package com.business.backendBusiness.service;


import com.business.backendBusiness.Repository.*;
import com.business.backendBusiness.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("v2")
@CrossOrigin
public class CreateService {

    public long id;
    public long idP;
    public long idE;
    public long idU;
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
    @Autowired
    private ImagenRepository imagenRepository;

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
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());
        }
        return null;
    }

    @PostMapping(path = "/employee")
    private Employee createEmployee(@RequestBody CreateData createData) {
        try {
            return employeeRepository.save(createData.getEmployee());
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());
        }
        return null;
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
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());
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
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());
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
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());

        }
        return null;
    }

    @PostMapping(path = "/set-password")
    private User setPassword(@RequestBody CreateData createData) {
        try {
            System.out.println("ingreso " + createData.getUser().getUser());
            if (validationSession(createData)) {
                System.out.println("ingreso");
                createData.getUser().setPassword(new EncodeUUID().encode(createData.getUser().getPassword()));

                createData.setUser(createData.getUser());
                return userRepository.save(createData.getUser());
            }

        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage() + "\nError Cause -> " + e.getCause());
            return null;
        }
        return null;
    }

    private boolean validationSession(CreateData createData) {
        try {

            Long user = createData.getHistorySession().getUserIduser();
            String idsession = createData.getHistorySession().getIdsession();
            System.out.println("user id -> " + user + "\nsession -> " + idsession);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(idsession, user);
            System.out.println("sesion encontrada -> " + historySession);
            if (historySession.isPresent()) {
                if (historySession.get().getStateIdstate() == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Validations -> " + e.getMessage() + "\nError  Validations  Cause -> " + e.getCause());
            return false;
        }
        return false;
    }


    @PostMapping(path = "/images")
    private ResponseEntity<Map<String, String>> test(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("description") String description) {
        Map<String, String> response = new HashMap<>();
        try {

            System.out.println("Se agrega "+description);
            guardarFile(file, description);
            response.put("message", "Successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getMessage()+ "\n" + e.getCause());
            response.put("message", "Failed to add");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    public void guardarFile(MultipartFile file, String description) throws IOException {
        byte[] data = file.getBytes();
        String dataDescription = file.getContentType();
        long id = imagenRepository.findAll().size();
        id++;
        ImageTest imagen = new ImageTest();
        imagen.setIdimagenes(id);
        imagen.setDescripcion(description);
        imagen.setImagen(data);
        imagenRepository.save(imagen);
    }


}
