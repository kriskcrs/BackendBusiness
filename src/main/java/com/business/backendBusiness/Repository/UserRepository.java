package com.business.backendBusiness.Repository;

import com.business.backendBusiness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, String> {

    public Optional<User> findByAndIduserAndPassword(String user, String password);

}
