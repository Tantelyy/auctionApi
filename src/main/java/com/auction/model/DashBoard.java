package com.auction.model;

import java.util.List;

import com.auction.model.category.Category;
import com.auction.model.tabBord.MoneyAuction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashBoard {
    private List<MoneyAuction> moneyAuctions;
    private Double totalComission;
    private List<Category> moneyAuctionCats;
}
