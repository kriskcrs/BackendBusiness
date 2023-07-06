package com.business.backendBusiness.entity;

public class CreateData  {

private Person person;
private Employee employee;
private User user;
private HistorySession historySession;
private Work work;
private Location location;
private State state;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HistorySession getHistorySession() {
        return historySession;
    }

    public void setHistorySession(HistorySession historySession) {
        this.historySession = historySession;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
