package com.meera.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "userregistration")
@Getter
@Setter
public class UserRegistration {



    @Id
    @Column(unique = true, nullable = false)
    private String email;

    private String username;
    private String password;
    private String address;
    private String role;

    @OneToMany(mappedBy = "userRegistration", cascade = CascadeType.ALL)
    private List<Product> products;
    // Getters and setters


}