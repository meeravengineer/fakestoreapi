package com.meera.models.Request;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRegistrationRequest {


    private String email;

    private String username;
    private String password;
    private String Address;
    private String Role;

    // Getters and setters


}