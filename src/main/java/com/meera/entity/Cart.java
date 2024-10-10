package com.meera.entity;
// Cart.java
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private Date date;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> products = new ArrayList<>();

    public void addCartItem(CartItem item) {
        products.add(item);
        item.setCart(this);
    }

    public void removeCartItem(CartItem item) {
        products.remove(item);
        item.setCart(null);
    }

    // getters and setters
}
