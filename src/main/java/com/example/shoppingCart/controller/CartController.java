package com.example.shoppingCart.controller;

import com.example.shoppingCart.model.ProductRequest;
import com.example.shoppingCart.model.Cart;
import com.example.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/createCart")
    public ResponseEntity<String> createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @PostMapping("/addToCart")
    public void addToCart(@RequestBody ProductRequest request){
            cartService.addToCart(request);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<String> removeProductFromCart(@RequestBody ProductRequest request){
        return cartService.removeProductFromCart(request);
    }


}
