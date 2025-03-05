package com.example.demoproductmanager.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String categoryName;
    private int quantity;
}
