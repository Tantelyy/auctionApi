package com.auction.model.balance;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.auction.model.Client;
import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BalanceLoadRequest extends HasId {
    @ManyToOne
    private Client client;

    @ManyToOne
    private BalanceState state;

    @Column(insertable = false)
    private Date requestDate;

    private Date treatmentDate;
    private String contact;
    private Double amount;
}