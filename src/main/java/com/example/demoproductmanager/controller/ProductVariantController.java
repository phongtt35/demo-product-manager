package com.example.demoproductmanager.controller;

import com.example.demoproductmanager.model.Product;
import com.example.demoproductmanager.model.ProductVariant;
import com.example.demoproductmanager.service.ProductService;
import com.example.demoproductmanager.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product-variants")
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    @Autowired
    private ProductService productService;


    @GetMapping("/new/{productId}")
    public String showCreateForm(@PathVariable Long productId, Model model) {
        model.addAttribute("productVariant", new ProductVariant());
        model.addAttribute("productId", productId);
        return "product-variants/form";
    }

    @PostMapping("/{productId}")
    public String saveProductVariant(@PathVariable Long productId, @ModelAttribute ProductVariant productVariant) {
        Product product = productService.findById(productId);
        productVariant.setProduct(product);
        productVariantService.save(productVariant);
        return "redirect:/products/productId=" + productId + "/variants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("productVariant", productVariantService.findById(id));
        model.addAttribute("products", productService.findAll());
        return "product-variants/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteById(id);
        return "redirect:/products";
    }
}