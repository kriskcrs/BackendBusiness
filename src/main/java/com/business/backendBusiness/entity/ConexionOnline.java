package com.business.backendBusiness.entity;

import java.util.Date;

public class ConexionOnline {


  private long idWork;
  private Date dateWork;
  private Date startTime;
  private Date endTime;
  private Integer totalHours;
  private String startGeo;
  private String endGeo;
  private Double rateToDay;
  private String user;
  private String state;

    public long getIdWork() {
        return idWork;
    }

    public void setIdWork(long idWork) {
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

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
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

    public Double getRateToDay() {
        return rateToDay;
    }

    public void setRateToDay(Double rateToDay) {
        this.rateToDay = rateToDay;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
