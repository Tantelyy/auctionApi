package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auction.exception.SetterValueWrongException;
import com.auction.model.category.Category;
import com.auction.model.category.CategoryDetails;
import com.auction.model.category.FormCategory;
import com.auction.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    ResponseEntity<Object> create(@RequestBody FormCategory formCategory) throws SetterValueWrongException {
        if (formCategory.getMaxDuration() < formCategory.getMinDuration()) {
            throw new SetterValueWrongException("La duree maximum doit etre superieur a la duree minimum");
        }
        if (formCategory.getDetails() != null) {
            for (CategoryDetails categoryDetails : formCategory.getDetails()) {
                if (categoryDetails.getName().equals("") || categoryDetails.getName() == null) {
                    throw new SetterValueWrongException("Invalid details category name");
                }
            }
        }

        return new ResponseEntity<>(categoryService.create(formCategory), HttpStatus.OK);
    }

    @PutMapping("/category/{id}/duration")
    ResponseEntity<Object> updateDuration(@RequestBody Category category, @PathVariable Integer id) throws SetterValueWrongException {
        if (category.getMaxDuration() < category.getMinDuration()) {
            throw new SetterValueWrongException("La duree maximum doit etre superieur a la duree minimum");
        }
        category.setId(id);
        return new ResponseEntity<>(categoryService.updateAuctionDuration(category), HttpStatus.OK);
    }

    @PutMapping("/category/{id}/comission")
    ResponseEntity<Object> updateComission(@RequestBody Category category, @PathVariable Integer id) throws SetterValueWrongException {
        category.setId(id);
        return new ResponseEntity<>(categoryService.updateAuctionComission(category), HttpStatus.OK);
    }

    @PostMapping("/category/{id}/details")
    ResponseEntity<Object> addDetail(@RequestBody CategoryDetails categoryDetails, @PathVariable Integer id) throws SetterValueWrongException {
        if (categoryDetails.getName().equals("") || categoryDetails.getName() == null) {
            throw new SetterValueWrongException("Invalid details category name");
        }
        return new ResponseEntity<>(categoryService.addDetail(categoryDetails, id), HttpStatus.OK);
    }

    @GetMapping("/category")
    ResponseEntity<Object> getAll() throws SetterValueWrongException {    
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<Object> findById(@PathVariable Integer id) throws SetterValueWrongException {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }


    
}
