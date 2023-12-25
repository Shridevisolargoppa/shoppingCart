package com.example.shoppingCart.service;

import com.example.shoppingCart.model.Cart;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.ProductRequest;
import com.example.shoppingCart.repository.CartRepository;
import com.example.shoppingCart.repository.ProductRepository;
import com.example.shoppingCart.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ValidationUtils validationUtils;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<String> createCart(Cart cart) {
        try {
            validationUtils.validateCart(cart.getCartId());
            validationUtils.validateUser(cart);
            validationUtils.validateProducts(cart);
            calculateTotalPrice(cart);
            Cart savedCart = cartRepository.save(cart);
            return ResponseEntity.status(HttpStatus.OK).body("Cart created successfully with the cartId" +
                    savedCart.getCartId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private void calculateTotalPrice(Cart cart) {
        Map<String, Integer> productquantityMap = cart.getProducts();
        double totalPrice = cart.getTotalPrice();
        for(Map.Entry<String, Integer> entry: productquantityMap.entrySet()){
            String productName = entry.getKey();
            Product product = productRepository.getProductByCategory(productName);
            totalPrice = totalPrice + product.getPrice() + product.getShippingCharge();
            cart.setTotalPrice(totalPrice);
        }
    }


    public ResponseEntity<String> addToCart(ProductRequest request) {
        try {
            Cart existingCart = validationUtils.cartValidation(request.getCartId());
            Product savedProduct = productRepository.getProductByCategory(request.getProductName());
            Map<String, Integer> productQuantityMap = existingCart.getProducts();
            for (Map.Entry<String, Integer> product : productQuantityMap.entrySet()) {
                String productName = product.getKey();
                int quantity = product.getValue();
                if (request.getProductName().equals(productName)) {
                    if (quantity < 10 && savedProduct.getQuantity() > quantity) {
                        productQuantityMap.put(request.getProductName(), quantity + request.getQuantity());
                        existingCart.setProducts(productQuantityMap);
                        calculateTotalPrice(existingCart);
                    }
                } else {
                    if (quantity <= 10 && savedProduct.getQuantity() > quantity) {
                        productQuantityMap.put(request.getProductName(), request.getQuantity());
                        existingCart.setProducts(productQuantityMap);
                        calculateTotalPrice(existingCart);
                    } else {
                        throw new RuntimeException("quantity should be less than or equals to ");
                    }
                }
                cartRepository.save(existingCart);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Product added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<String> removeProductFromCart(ProductRequest request) {
        Cart existingCart = validationUtils.cartValidation(request.getCartId());
        Map<String, Integer> productQuantityMap = existingCart.getProducts();
        Set<String> products = productQuantityMap.keySet();
        if(products.contains(request.getProductName())){
           int quantity = productQuantityMap.get(request.getProductName());
           if(quantity>=request.getQuantity()){
               productQuantityMap.put(request.getProductName(), quantity-request.getQuantity());
               existingCart.setProducts(productQuantityMap);
           }
        }

        for(Map.Entry<String, Integer> entry : productQuantityMap.entrySet()){
            String productName = entry.getKey();
            int quantity = entry.getValue();
            if(quantity == 0){
                productQuantityMap.remove(productName, quantity);
                existingCart.setProducts(productQuantityMap);
            }
        }

        if(ObjectUtils.isEmpty(productQuantityMap)) {
            cartRepository.delete(existingCart);
        }

        cartRepository.save(existingCart);
        return ResponseEntity.status(HttpStatus.OK).body("Removed successfully for the cart" +existingCart.getCartId());
    }



}