package com.meera.services;

import com.meera.entity.UserRegistration;
import com.meera.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public LoginService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    public boolean authenticateUser(String email, String password) {
        UserRegistration user = userRegistrationRepository.findByEmailAndPassword(email, password);
        return user != null;
    }
}