package com.auction.model.category;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.auction.model.common.HasName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends HasName {
    private String reference;
    private Double minDuration;
    private Double maxDuration;
    private String picture;
    private Double comission;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private List<CategoryDetails> details;

    @Transient
    private Double totalComission = 0.0;
}
