package com.meera.services;

import com.meera.entity.Product;
import com.meera.entity.UserRegistration;
import com.meera.repositories.ProductRepository;
import com.meera.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRegistrationRepository userRegistrationRepository) {
        this.productRepository = productRepository;
        this.userRegistrationRepository = userRegistrationRepository;
    }

    public Product createProduct(Product product, String email) {
        UserRegistration user = userRegistrationRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        product.setUserRegistration(user);
        return productRepository.save(product);
    }

    public List<Product> getProductsByEmail(String email) {
        return productRepository.findByUserRegistration_Email(email);
    }

    public Product updateProduct(Long productId, String email, Product productDetails) {
        return productRepository.findByIdAndUserRegistration_Email(productId, email)
                .map(existingProduct -> {
                    existingProduct.setTitle(productDetails.getTitle());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setDescription(productDetails.getDescription());
                    existingProduct.setImage(productDetails.getImage());
                    existingProduct.setCategory(productDetails.getCategory());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found or does not belong to the user"));
    }

    @Transactional
    public void deleteProductsByEmail(String email) {
        productRepository.deleteByUserRegistration_Email(email);
    }

    @Transactional
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}