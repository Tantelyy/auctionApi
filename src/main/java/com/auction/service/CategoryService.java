package com.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.category.Category;
import com.auction.model.category.CategoryDetails;
import com.auction.model.category.FormCategory;
import com.auction.repository.category.CategoryDetailsRepository;
import com.auction.repository.category.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryDetailsRepository categoryDetailsRepository;

    @Transactional(rollbackOn = Exception.class)
    public boolean create(FormCategory formCategory) {
        Category category = new Category();
        category.setName(formCategory.getName());
        category.setComission(formCategory.getComission());
        category.setMaxDuration(formCategory.getMaxDuration());
        category.setMinDuration(formCategory.getMinDuration());
        category.setPicture(formCategory.getPicture());
        category.setReference(formCategory.getReference());

        Category newCategory = categoryRepository.save(category);
        if (formCategory.getDetails() == null) {
            return true;
        }

        for (CategoryDetails categoryDetails : formCategory.getDetails()) {
            categoryDetails.setCategoryId(newCategory.getId());
        }
        categoryDetailsRepository.saveAll(formCategory.getDetails());

        return true;
    }

    public boolean updateAuctionDuration(Category newCategory) {
        Category category = categoryRepository.findById(newCategory.getId()).get();
        category.setMaxDuration(newCategory.getMaxDuration());
        category.setMinDuration(newCategory.getMinDuration());
        categoryRepository.save(category);
        return true;
    }

    public boolean updateAuctionComission(Category newCategory) {
        Category category = categoryRepository.findById(newCategory.getId()).get();
        category.setComission(newCategory.getComission());
        categoryRepository.save(category);
        return true;
    }

    public boolean addDetail(CategoryDetails categoryDetails, Integer id) {
        categoryDetails.setCategoryId(id);
        categoryDetailsRepository.save(categoryDetails);
        return true;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).get() ;
    }
}
