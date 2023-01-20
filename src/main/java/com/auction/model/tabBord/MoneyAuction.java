package com.auction.model.tabBord;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.auction.model.Client;
import com.auction.model.auction.Auction;
import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

//liste des comission recoltes
@Entity(name = "v_money_auction")
@Getter
@Setter
public class MoneyAuction extends HasId{
    @ManyToOne
    private Client winningClient;
    @ManyToOne
    private Client ownerClient;
    @ManyToOne
    private Auction auction;
    private Date dateBet;
    private Double clientAmount;
    private Double amountComission;
    private Double comission;
    private Date endDate;
}
