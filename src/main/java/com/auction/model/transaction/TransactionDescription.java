package com.auction.model.transaction;

import javax.persistence.Entity;

import com.auction.model.common.HasName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TransactionDescription extends HasName {
    private Integer natureId;
}
