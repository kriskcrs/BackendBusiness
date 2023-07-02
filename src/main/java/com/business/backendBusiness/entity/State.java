package com.business.backendBusiness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="state")
public class State {
    @Id
    @Column(name="idstate", nullable = false)
    private Long idstate;

    @Column(name="state")
    private String state;

    public Long getIdstate() {
        return idstate;
    }

    public void setIdstate(Long idstate) {
        this.idstate = idstate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
