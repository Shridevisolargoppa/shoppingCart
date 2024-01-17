package com.example.shoppingCart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    // existing fields.
    @Id

    Long id;

    String cartId;

    double totalPrice;

    String userId;
    @ElementCollection
    @CollectionTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "product_name")
    @Column(name = "quantity")
    private Map<String, Integer> products = new HashMap<>();

}

