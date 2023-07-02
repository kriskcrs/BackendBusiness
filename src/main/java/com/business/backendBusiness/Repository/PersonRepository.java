package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person, Long> {
}
