package com.auction.model.category;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.auction.model.common.HasName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CategoryDetails extends HasName {
    @Column(name = "category_id")
    private Integer categoryId;
}