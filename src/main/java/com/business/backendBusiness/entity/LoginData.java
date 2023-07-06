package com.business.backendBusiness.entity;

import java.util.Optional;

public class LoginData {

    private Optional<User> user;
    private Optional<HistorySession> historySession;

    public LoginData(Optional<User> user, Optional<HistorySession> historySession) {
        this.user = user;
        this.historySession = historySession;
    }

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }

    public Optional<HistorySession> getHistorySession() {
        return historySession;
    }

    public void setHistorySession(Optional<HistorySession> historySession) {
        this.historySession = historySession;
    }
}
