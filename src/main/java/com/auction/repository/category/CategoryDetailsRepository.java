package com.auction.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.model.category.CategoryDetails;

public interface CategoryDetailsRepository extends JpaRepository<CategoryDetails, Integer> {
    
}
