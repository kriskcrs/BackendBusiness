package com.business.backendBusiness.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idwork", nullable = false)
    private Integer idWork;
    @Column(name = "date_work")
    private Date dateWork;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "total_hour")
    private Integer totalHour;
    @Column(name = "start_geo")
    private Integer startGeo;
    @Column(name = "end_geo")
    private Integer endGeo;
    @Column(name = "rate_today")
    private Integer rateToday;


    public Integer getIdWork() {
        return idWork;
    }

    public void setIdWork(Integer idWork) {
        this.idWork = idWork;
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

    public Integer getStartGeo() {
        return startGeo;
    }

    public void setStartGeo(Integer startGeo) {
        this.startGeo = startGeo;
    }

    public Integer getEndGeo() {
        return endGeo;
    }

    public void setEndGeo(Integer endGeo) {
        this.endGeo = endGeo;
    }

    public Integer getRateToday() {
        return rateToday;
    }

    public void setRateToday(Integer rateToday) {
        this.rateToday = rateToday;
    }
}
