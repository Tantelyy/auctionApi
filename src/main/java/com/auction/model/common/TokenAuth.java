package com.auction.model.common;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class TokenAuth extends HasId {
    @Column
    private String token;

    @Column
    private Date expirationDate;
}
