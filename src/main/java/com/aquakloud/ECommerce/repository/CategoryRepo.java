package com.aquakloud.ECommerce.repository;

import com.aquakloud.ECommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
