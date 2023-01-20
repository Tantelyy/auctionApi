package com.auction.repository.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.auction.AuctionBet;

@Repository
public interface AuctionBetRepository extends JpaRepository<AuctionBet, Integer> {
    
}
