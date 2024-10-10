package com.meera.contollers;

import com.meera.entity.Cart;
import com.meera.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createOrUpdateCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.createOrUpdateCart(cart);
        return ResponseEntity.ok(savedCart);
    }
    @PutMapping("/{email}")
    public ResponseEntity<Cart> updateCart(@PathVariable String email, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(email, cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCart(@PathVariable String email) {
        cartService.deleteCart(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Cart> viewCart(@PathVariable String email) {
        Cart cart = cartService.viewCart(email);
        return ResponseEntity.ok(cart);
    }
}