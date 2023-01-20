package com.auction.model.auction;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.auction.model.Client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AuctionBet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column(name = "auction_id")
    private Integer auctionId;

    @Column(name = "date_bet")
    private Date dateBet;

    @Column(name = "amount")
    private Double amount;

}
