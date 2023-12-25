package com.example.shoppingCart.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Transient;
import jakarta.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class Product {


        @Transient
        public static final String SEQUENCE_NAME="Ordersequence";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        int id;

        String category;

        String colour;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getColour() {
                return colour;
        }

        public void setColour(String colour) {
                this.colour = colour;
        }

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public double getShippingCharge() {
                return shippingCharge;
        }

        public void setShippingCharge(double shippingCharge) {
                this.shippingCharge = shippingCharge;
        }

        Double price;

        String brand;

        int quantity;

        double shippingCharge;

}
