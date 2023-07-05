package com.business.backendBusiness.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "iduser", nullable = false)
    private Long iduser;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;



    //fk
    @Column(name = "employee_idemployee")
    private Long employeeIdemployee;

    @Column(name ="rol_idrol")
    private Long rolIdrol;

    public Long getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(Long rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Long employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }
}
