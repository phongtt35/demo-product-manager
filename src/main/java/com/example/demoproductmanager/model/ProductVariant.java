package com.example.demoproductmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;
    private int quantity;
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}