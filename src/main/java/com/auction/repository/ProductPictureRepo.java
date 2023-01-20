package com.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.ProductPicture;

@Repository
public interface ProductPictureRepo  extends JpaRepository<ProductPicture, Integer> {
    
}
