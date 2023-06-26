package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
