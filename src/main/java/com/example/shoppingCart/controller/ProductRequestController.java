package com.example.shoppingCart.controller;

import com.example.shoppingCart.model.ProductRequest;
import com.example.shoppingCart.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productRequests")
public class ProductRequestController {

    @Autowired
    private ProductRequestService productRequestService;

    @GetMapping("/{cartId}")
    public ResponseEntity<ProductRequest> getProductRequest(@PathVariable String cartId) {
        ProductRequest productRequest = productRequestService.getProductRequestById(cartId);
        if (productRequest != null) {
            return ResponseEntity.ok(productRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductRequest(@RequestBody ProductRequest productRequest) {
        try {
            productRequestService.addProductRequest(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product request added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeProductRequest(@PathVariable String cartId) {
        try {
            productRequestService.removeProductRequest(cartId);
            return ResponseEntity.ok("Product request removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody ProductRequest productRequest) {
        try {
            productRequestService.updateProductRequestQuantity(productRequest);
            return ResponseEntity.ok("Product request quantity updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
