package com.example.demoproductmanager.repository;

import com.example.demoproductmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
