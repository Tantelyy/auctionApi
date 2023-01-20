package com.auction.repository.tableauBord;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.model.tabBord.MoneyAuction;

public interface MoneyAuctionRepo extends JpaRepository<MoneyAuction, Integer>{
}
