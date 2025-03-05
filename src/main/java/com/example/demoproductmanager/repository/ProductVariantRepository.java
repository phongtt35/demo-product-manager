package com.example.demoproductmanager.repository;

import com.example.demoproductmanager.model.Product;
import com.example.demoproductmanager.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProduct(Product product);
}
