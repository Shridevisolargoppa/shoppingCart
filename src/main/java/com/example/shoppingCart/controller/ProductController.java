package com.example.shoppingCart.controller;

import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;
@GetMapping("/getallproducts")
public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
}
    @GetMapping("/findById/{productId}")
    public Product findById(@PathVariable int productId) throws Exception {
        return productService.findByID(productId);
    }

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
}
