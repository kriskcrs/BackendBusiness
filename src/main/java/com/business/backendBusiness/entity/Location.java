package com.business.backendBusiness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="location")
public class Location {

    @Id
    @Column(name="idlocation", nullable = false)
    private Integer Idlocation;

    @Column(name="location")
    private String location;

    public Integer getIdlocation() {
        return Idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        Idlocation = idlocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
