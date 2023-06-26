package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository <Work,Integer> {
}
