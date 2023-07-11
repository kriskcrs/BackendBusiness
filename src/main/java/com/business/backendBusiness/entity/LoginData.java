package com.business.backendBusiness.entity;

public class LoginData {

    private User user;
    private HistorySession historySession;
    private String Message;


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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
