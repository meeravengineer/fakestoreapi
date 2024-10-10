package com.meera.repositories;

import com.meera.entity.UserRegistration;
import com.meera.models.Request.UserRegistrationRequest;
import com.meera.models.Response.ViewUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, String> {
    Optional<UserRegistration> findByEmail(String email);
    UserRegistration findByEmailAndPassword(String email, String password);


    void deleteByEmail(String email);
}