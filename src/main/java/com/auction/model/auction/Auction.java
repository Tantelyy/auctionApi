package com.auction.model.auction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.auction.model.Client;
import com.auction.model.ProductPicture;
import com.auction.model.category.CategoryDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryDetails categoryDetails;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "reference")
    private String reference;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "depository_date")
    private Timestamp depositoryDate;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "amount_min")
    private Double amountMin;
    
    @Column(name = "comission")
    private Double comission;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "auction_id")
    private List<AuctionBet> auctionBetList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private List<ProductPicture> productPictureList;

    public AuctionBet retrieveLastAuctionBet() {
        List<AuctionBet> bets = getAuctionBetList();
        if(bets.size() == 0){
            throw null;
        }
        Collections.sort(bets, Comparator.comparing(AuctionBet::getAmount).reversed());
        return bets.get(0);
    }
}
