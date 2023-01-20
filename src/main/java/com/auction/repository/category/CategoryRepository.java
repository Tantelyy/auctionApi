package com.auction.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
