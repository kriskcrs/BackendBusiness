package com.business.backendBusiness.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "iduser", nullable = false)
    private String iduser;


    @Column(name = "password")
    private String password;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
