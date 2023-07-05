package com.business.backendBusiness.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "history_session")
public class HistorySession {
    @Id
    @Column(name = "idhistory_session", nullable = false)
    private Long idhistorySession;
    @Column(name = "idsession")
    private String idsession;
    @Column(name = "user_iduser")
    private Long userIduser;
    @Column(name = "state_idstate")
    private Long stateIdstate;

    public Long getIdhistorySession() {
        return idhistorySession;
    }

    public void setIdhistorySession(Long idhistorySession) {
        this.idhistorySession = idhistorySession;
    }

    public String getIdsession() {
        return idsession;
    }

    public void setIdsession(String idsession) {
        this.idsession = idsession;
    }

    public Long getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(Long userIduser) {
        this.userIduser = userIduser;
    }

    public Long getStateIdstate() {
        return stateIdstate;
    }

    public void setStateIdstate(Long stateIdstate) {
        this.stateIdstate = stateIdstate;
    }
}
