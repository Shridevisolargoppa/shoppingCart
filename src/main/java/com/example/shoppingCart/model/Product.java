package com.example.shoppingCart.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Transient;
import jakarta.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
public class Product {


        @Transient
        public static final String SEQUENCE_NAME="Ordersequence";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        int id;

        String category;

        String colour;

        Double price;

        String brand;

        int quantity;

        double shippingCharge;

}
