package com.auction.model.balance;

import javax.persistence.Entity;

import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "balance_mvt")
public class Balance extends HasId {
    private Double amount;
    private Integer clientId;
}
