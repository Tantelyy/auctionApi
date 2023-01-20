package com.auction.model.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class HasName extends HasId{
    
    @Column
    private String name;
}
