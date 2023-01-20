package com.auction.model.login;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.auction.model.Client;
import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoTokenAuth extends HasId{
    @ManyToOne
    private Client client;
    @Column
    private String token;
    @Column 
    private Date expirationDate;
}
