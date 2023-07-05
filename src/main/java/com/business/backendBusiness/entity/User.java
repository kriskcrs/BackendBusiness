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
    @Column(name = "person_idperson")
    private Long personIdperson;

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

    public Long getPersonIdperson() {
        return personIdperson;
    }

    public void setPersonIdperson(Long personIdperson) {
        this.personIdperson = personIdperson;
    }


}
