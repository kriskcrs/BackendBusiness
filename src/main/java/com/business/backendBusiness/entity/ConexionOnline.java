package com.business.backendBusiness.entity;

import java.util.List;

public class ConexionOnline {

   
private List<Work> workList;
private List<HistorySession> historySessionList;
private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    public List<HistorySession> getHistorySessionList() {
        return historySessionList;
    }

    public void setHistorySessionList(List<HistorySession> historySessionList) {
        this.historySessionList = historySessionList;
    }
}
