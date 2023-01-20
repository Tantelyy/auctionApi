package com.auction.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends HasId {
    @Column
    private Integer genderId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date birthDate;
    @Column
    private String contact;
}
