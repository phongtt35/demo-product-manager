package com.example.demoproductmanager.service;

import com.example.demoproductmanager.model.Product;
import com.example.demoproductmanager.model.ProductVariant;
import com.example.demoproductmanager.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<ProductVariant> findByProduct(Product product) {
        return productVariantRepository.findByProduct(product);
    }

    public ProductVariant findById(Long id) {
        return productVariantRepository.findById(id).orElse(null);
    }

    public ProductVariant save(ProductVariant productVariant) {
        return productVariantRepository.save(productVariant);
    }

    public void deleteById(Long id) {
        productVariantRepository.deleteById(id);
    }
}