package com.example.demoproductmanager.service;

import com.example.demoproductmanager.model.Product;
import com.example.demoproductmanager.model.ProductDto;
import com.example.demoproductmanager.model.ProductVariant;
import com.example.demoproductmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantService productVariantService;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public int calculateTotalQuantity(Product product) {
        List<ProductVariant> variants = productVariantService.findByProduct(product);
        return variants.stream().mapToInt(ProductVariant::getQuantity).sum();
    }

    public BigDecimal[] findMinAndMaxPrice(Product product) {
        List<ProductVariant> variants = productVariantService.findByProduct(product);
        BigDecimal minPrice = variants.stream()
                .map(ProductVariant::getPrice).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        BigDecimal maxPrice = variants.stream()
                .map(ProductVariant::getPrice).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        return new BigDecimal[]{minPrice, maxPrice};
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());

        BigDecimal[] prices = findMinAndMaxPrice(product);
        dto.setMinPrice(prices[0]);
        dto.setMaxPrice(prices[1]);

        dto.setCategoryName(product.getCategory().getName());
        dto.setQuantity(calculateTotalQuantity(product));
        return dto;
    }
}