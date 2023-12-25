package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(String userId);

    Cart findBycartId(String cartId);
}
