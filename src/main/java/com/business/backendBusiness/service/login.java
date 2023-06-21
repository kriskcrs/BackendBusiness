package com.business.backendBusiness.service;

import com.business.backendBusiness.entity.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin

public class login {


    @PostMapping(path = "/outh")
    private Employee Authentication(@RequestBody Employee employee) {

        if(employee.getUser().equals("admin") && employee.getPassword().equals("admin")){

        }else{
            employee.setPassword(null);
            employee.setUser(null);
        }

        return employee;
    }

}
