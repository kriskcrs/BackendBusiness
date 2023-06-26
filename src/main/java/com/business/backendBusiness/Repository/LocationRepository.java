package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Integer> {
}
