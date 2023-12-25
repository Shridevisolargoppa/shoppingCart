package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByColourAndBrandAndCategory(String colour, String brand, String category);
    void deleteById(int id);
    Product findProductById(int productId);
    Product getProductByCategory(String productName);

}
