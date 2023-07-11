package com.business.backendBusiness.service;

import com.business.backendBusiness.Repository.HistorySessionRepository;
import com.business.backendBusiness.entity.CreateData;
import com.business.backendBusiness.entity.HistorySession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class KeepAlive {

    @Autowired
    HistorySessionRepository historySessionRepository;

    public boolean sessionValid = false;

    public boolean validationSession(CreateData createData) {
        try {
            long iduser = createData.getUser().getIduser();
            String idsession = createData.getHistorySession().getIdsession();
            System.out.println("user id -> " + iduser + "\nsession -> " + idsession);
            Optional<HistorySession> historySession = historySessionRepository.findByIdsessionAndUserIduser(idsession, iduser);
            System.out.println(historySession);
            if (historySession.isPresent()) {
                if (historySession.get().getStateIdstate() == 1) {
                    return sessionValid = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Validation -> " + e.getCause());
        }
        return sessionValid = false;
    }


}


