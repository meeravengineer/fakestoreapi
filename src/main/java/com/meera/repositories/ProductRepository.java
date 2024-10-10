package com.meera.repositories;

import com.meera.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserRegistration_Email(String email);
    Optional<Product> findByIdAndUserRegistration_Email(Long id, String email);
    void deleteByUserRegistration_Email(String email);
    Optional<Product> findById(Long id);

}