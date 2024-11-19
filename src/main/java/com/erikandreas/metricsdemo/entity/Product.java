package com.erikandreas.metricsdemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private double price;

    @Column(name = "isOfferApplied")
    private boolean isOfferApplied = false;

    @Column(name = "discountPercentage")
    private double discountPercentage = 0.0;

    @Column(name = "priceAfterDiscount")
    private double priceAfterDiscount = 0.0;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

}
