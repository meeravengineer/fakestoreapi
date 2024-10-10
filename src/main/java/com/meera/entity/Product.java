package com.meera.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "products")
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserRegistration userRegistration;
    // Constructors, getters, and setters
}