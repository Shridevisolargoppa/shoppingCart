package com.example.shoppingCart.service;

import com.example.shoppingCart.exception.DuplicateProductException;
import com.example.shoppingCart.exception.ProductNotFoundException;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.example.shoppingCart.constants.Constants.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<String> createProduct(Product product) {
        try {
            Product product1 = productRepository.findProductById(product.getId());
            if (!ObjectUtils.isEmpty(product1)) {
                throw new DuplicateProductException(PRODUCT_ALREADY_EXISTS + " " + "with the Id" + " " + product1.getId());
            }
            if (productRepository.existsByColourAndBrandAndCategory(product.getColour(), product.getBrand(),
                    product.getCategory())) {
                throw new DuplicateProductException(DUPLICATE_PRODUCT);
            }
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully with the Id" +
                    savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<String> removeProduct(int productId) {
        try {
            Product product = productRepository.findProductById(productId);
            if (ObjectUtils.isEmpty(product)) {
                throw new ProductNotFoundException(PRODUCT_DOES_NOT_EXISTS);
            }
            productRepository.deleteById(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Product Removed Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    public Product findByID(int productId) {
        Product product = productRepository.findProductById(productId);
        if (ObjectUtils.isEmpty(product)) {
            throw new ProductNotFoundException(PRODUCT_DOES_NOT_EXISTS);
        }
        return product;
    }
}
