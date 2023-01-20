package com.auction.model.category;

import java.util.List;

import com.auction.exception.SetterValueWrongException;

import lombok.Getter;

@Getter
public class FormCategory {
    private String name;
    private String reference;
    private Double minDuration;
    private Double maxDuration;
    private String picture;
    private Double comission;
    private List<CategoryDetails> details;

    public void setName(String name) throws SetterValueWrongException {
        if (name.equals("") || name == null) {
            throw new SetterValueWrongException("Invalid name");
        }
        this.name = name;
    }

    public void setReference(String reference) throws SetterValueWrongException {
        if (name.equals("") || name == null) {
            throw new SetterValueWrongException("Invalid reference");
        }
        this.reference = reference;
    }

    public void setMinDuration(Double minDuration) throws SetterValueWrongException {
        if (minDuration == 0 || minDuration == null) {
            throw new SetterValueWrongException("Invalid minimum duration");
        }
        this.minDuration = minDuration;
    }

    public void setMaxDuration(Double maxDuration) throws SetterValueWrongException {
        if (maxDuration == 0 || maxDuration == null) {
            throw new SetterValueWrongException("Invalid maximum duration");
        }
        this.maxDuration = maxDuration;
    }

    public void setPicture(String picture) throws SetterValueWrongException {
        if (picture.equals("") || picture == null) {
            throw new SetterValueWrongException("Invalid picture");
        }
        this.picture = picture;
    }

    public void setCommission(Double comission) throws SetterValueWrongException {
        if (comission <= 0 || comission == null) {
            throw new SetterValueWrongException("Invalid commission");
        }
        this.comission = comission;
    }

    public void setDetails(List<CategoryDetails> details) {
        this.details = details;
    }
}
