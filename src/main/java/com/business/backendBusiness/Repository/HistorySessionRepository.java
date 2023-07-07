package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.HistorySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistorySessionRepository extends JpaRepository<HistorySession,Long> {

    public Optional<HistorySession> findByIdsessionAndUserIduser(String idsession,Long user);

    public HistorySession deleteHistorySessionByIdsession(String idsession);
}
