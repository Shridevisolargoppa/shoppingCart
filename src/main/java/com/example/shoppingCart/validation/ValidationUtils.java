package com.example.shoppingCart.validation;

import com.example.shoppingCart.model.Cart;
import com.example.shoppingCart.repository.CartRepository;
import com.example.shoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class ValidationUtils {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;


    public void validateCart(String cartId) {
        Cart existingCart = cartRepository.findBycartId(cartId);
        if (!ObjectUtils.isEmpty(existingCart)) {
            throw new RuntimeException("Cart already exist" + cartId);
        }
    }

    public void validateUser(Cart cart) {
        Cart existingCart = cartRepository.findByUserId(cart.getUserId());
        if (!ObjectUtils.isEmpty(existingCart)) {
            throw new RuntimeException("User already exists for the cart" + cart.getCartId());
        }
    }

    public Cart cartValidation(String cartId) {
        Cart cart = cartRepository.findBycartId(cartId);
        if (ObjectUtils.isEmpty(cart)) {
            throw new RuntimeException("Cart Does Not Exist");
        }
        return cart;
    }

}
