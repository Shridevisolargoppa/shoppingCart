package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, String> {
}
