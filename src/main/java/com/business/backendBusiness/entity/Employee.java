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
    @Column(name="dob_date")
    private Date dobDate;
    @Column(name="person_idperson")
    private Integer personIdperson;
    @Column(name="location_idlocation")
    private Integer locationIdlocation;
    @Column(name="state_idstate")
    private Integer stateIdstate;

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

    public Date getDobDate() {
        return dobDate;
    }

    public void setDobDate(Date dobDate) {
        this.dobDate = dobDate;
    }

    public Integer getPersonIdperson() {
        return personIdperson;
    }

    public void setPersonIdperson(Integer personIdperson) {
        this.personIdperson = personIdperson;
    }

    public Integer getLocationIdlocation() {
        return locationIdlocation;
    }

    public void setLocationIdlocation(Integer locationIdlocation) {
        this.locationIdlocation = locationIdlocation;
    }

    public Integer getStateIdstate() {
        return stateIdstate;
    }

    public void setStateIdstate(Integer stateIdstate) {
        this.stateIdstate = stateIdstate;
    }
}


