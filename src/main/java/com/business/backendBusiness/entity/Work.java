package com.business.backendBusiness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name ="work")
public class Work {

    @Id
    @Column(name="idwork", nullable = false)
    private Long idwork;

    @Column(name="date_work")
    private Date dateWork;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="total_hour")
    private Integer totalHour;

    @Column(name="start_geo")
    private String startGeo;

    @Column(name="end_geo")
    private String endGeo;

    @Column(name="rate_today")
    private Double rateToday;

    @Column(name="employee_idemployee")
    private Integer employeeIdemployee;

    @Column(name="state_idstate")
    private Integer stateIdstate;

    @Column(name = "idsession")
    private String idsession;

    public Long getIdwork() {
        return idwork;
    }

    public void setIdwork(Long idwork) {
        this.idwork = idwork;
    }

    public Date getDateWork() {
        return dateWork;
    }

    public void setDateWork(Date dateWork) {
        this.dateWork = dateWork;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Integer totalHour) {
        this.totalHour = totalHour;
    }

    public String getStartGeo() {
        return startGeo;
    }

    public void setStartGeo(String startGeo) {
        this.startGeo = startGeo;
    }

    public String getEndGeo() {
        return endGeo;
    }

    public void setEndGeo(String endGeo) {
        this.endGeo = endGeo;
    }

    public Double getRateToday() {
        return rateToday;
    }

    public void setRateToday(Double rateToday) {
        this.rateToday = rateToday;
    }

    public Integer getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Integer employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    public Integer getStateIdstate() {
        return stateIdstate;
    }

    public void setStateIdstate(Integer stateIdstate) {
        this.stateIdstate = stateIdstate;
    }

    public String getIdsession() {
        return idsession;
    }

    public void setIdsession(String idsession) {
        this.idsession = idsession;
    }
}
