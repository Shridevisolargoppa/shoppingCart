package com.example.shoppingCart.controller;

import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        ResponseEntity<String> response = productService.createProduct(product);
        return response;
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable int productId) {
        ResponseEntity<String> response = productService.removeProduct(productId);
        return response;
    }

    @GetMapping("/findAll")
    public List<Product> fetchAllProducts(){
       return productService.fetchAllProducts();
    }

    @GetMapping("/findById/{ProductId}")
    public Product findById(@PathVariable int productId){
       return productService.findByID(productId);
    }
}
