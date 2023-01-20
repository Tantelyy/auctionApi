package com.auction.model.login;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Column;

import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoTokenAuth extends HasId{

    @Column
    private Integer adminId;
    @Column
    private String token;
    @Column
    private Date expirationDate;
}
