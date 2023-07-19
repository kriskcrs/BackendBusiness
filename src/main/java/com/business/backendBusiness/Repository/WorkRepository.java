package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository  extends JpaRepository<Work,Long> {

    public List<Work> findByEmployeeIdemployee(long employee);
}
