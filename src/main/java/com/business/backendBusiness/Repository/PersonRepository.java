package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonRepository  extends JpaRepository<Person, Long> {
    public List<Person> findByFirstNameOrLastName(String firstName, String LastName);
}
