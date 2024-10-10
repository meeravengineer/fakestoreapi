package com.meera.services;

import com.meera.entity.UserRegistration;
import com.meera.models.Response.ViewUserDetails;
import com.meera.repositories.UserRegistrationRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationService(UserRegistrationRepository loginRepository) {
        this.userRegistrationRepository = loginRepository;
    }

    public boolean findUserByEmail(String email){
        return userRegistrationRepository.findByEmail(email).isPresent();
    }
    public Optional<ViewUserDetails> getUserDetailsByEmail(String email) {
        return userRegistrationRepository.findByEmail(email)
                .map(this::convertToViewUserDetails);
    }

    public ViewUserDetails createNewUser(UserRegistration userRegistration) {
        if(findUserByEmail(userRegistration.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        UserRegistration user = userRegistrationRepository.save(userRegistration);
        return convertToViewUserDetails(user);
    }

    public ViewUserDetails updateUser(UserRegistration updateUserRegistration) {
        return convertToViewUserDetails(userRegistrationRepository.save(updateUserRegistration));
    }

    @Transactional
    public boolean deleteUser(String email) {
        if (!findUserByEmail(email)) {
            throw new RuntimeException("Email is not registered");
        } else {
            userRegistrationRepository.deleteByEmail(email);
            return true;
        }
    }

    private ViewUserDetails convertToViewUserDetails(UserRegistration userRegistration) {
        return new ViewUserDetails(
                userRegistration.getEmail(),
                userRegistration.getRole(),
                userRegistration.getUsername(),
                userRegistration.getAddress()
        );
    }


}