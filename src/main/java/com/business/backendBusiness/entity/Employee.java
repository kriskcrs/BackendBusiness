package com.business.backendBusiness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "idemployee", nullable = false)
    private Long idemployee;
    @Column(name = "rate")
    private Double rate;
    @Column(name="hire_date")
    private Date hireDate;

    @Column(name="person_idperson")
    private Long personIdperson;
    @Column(name="location_idlocation")
    private Long locationIdlocation;
    @Column(name="state_idstate")
    private Long stateIdstate;

    public Long getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Long idemployee) {
        this.idemployee = idemployee;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Long getPersonIdperson() {
        return personIdperson;
    }

    public void setPersonIdperson(Long personIdperson) {
        this.personIdperson = personIdperson;
    }

    public Long getLocationIdlocation() {
        return locationIdlocation;
    }

    public void setLocationIdlocation(Long locationIdlocation) {
        this.locationIdlocation = locationIdlocation;
    }

    public Long getStateIdstate() {
        return stateIdstate;
    }

    public void setStateIdstate(Long stateIdstate) {
        this.stateIdstate = stateIdstate;
    }
}


