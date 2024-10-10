package com.meera.services;

import com.meera.entity.Cart;
import com.meera.entity.CartItem;
import com.meera.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

        @Autowired
        private CartRepository cartRepository;

        @Transactional
        public Cart createOrUpdateCart(Cart cartData) {
            Cart cart = cartRepository.findByEmail(cartData.getEmail())
                    .orElse(new Cart());

            cart.setEmail(cartData.getEmail());
            cart.setDate(cartData.getDate());

            cart.getProducts().clear();

            for (CartItem itemData : cartData.getProducts()) {
                CartItem item = new CartItem();
                item.setProductId(itemData.getProductId());
                item.setQuantity(itemData.getQuantity());
                cart.addCartItem(item);
            }

            return cartRepository.save(cart);
        }

        @Transactional
        public Cart updateCart(String email, Cart cartData) {
            Cart cart = cartRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Cart not found"));

            cart.setDate(cartData.getDate());

            cart.getProducts().clear();

            for (CartItem itemData : cartData.getProducts()) {
                CartItem item = new CartItem();
                item.setProductId(itemData.getProductId());
                item.setQuantity(itemData.getQuantity());
                cart.addCartItem(item);
            }

            return cartRepository.save(cart);
        }

        @Transactional
        public void deleteCart(String email) {
            Cart cart = cartRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Cart not found"));
            cartRepository.delete(cart);
        }

        public Cart viewCart(String email) {
            return cartRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Cart not found"));
        }
    }