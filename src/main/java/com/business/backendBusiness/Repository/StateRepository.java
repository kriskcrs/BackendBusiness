package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository <State, Integer> {
}
