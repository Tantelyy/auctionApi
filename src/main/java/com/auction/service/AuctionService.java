package com.auction.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.ProductPicture;
import com.auction.model.auction.Auction;
import com.auction.model.auction.AuctionBet;
import com.auction.model.category.Category;
import com.auction.model.category.CategoryDetails;
import com.auction.repository.ProductPictureRepo;
import com.auction.repository.auction.AuctionBetRepository;
import com.auction.repository.auction.AuctionRepository;
import com.auction.repository.auction.AuctionSearch;
import com.auction.repository.category.CategoryDetailsRepository;
import com.auction.repository.category.CategoryRepository;
import com.auction.service.login.LoginService;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private AuctionBetRepository auctionBetRepo;

    @Autowired
    private CategoryDetailsRepository categoryDetailsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductPictureRepo productPictureRepo;

    @Autowired
    private LoginService ls;


    public List<Auction> search(AuctionSearch search) {
        String motCle = (search.getMotCle() == null) ? "" : search.getMotCle();
        String str = "2017-12-03";
        Date date = Date.valueOf(str);
        System.out.println("Date Value: " + date);
        Date minDate = (search.getMinDate() == null) ? Date.valueOf("2000-01-01") : search.getMinDate();
        Date maxDate = (search.getMaxDate() == null) ? Date.valueOf("2030-01-01") : search.getMaxDate();
        Double minAmount = (search.getMinAmount() == null) ? 0 : search.getMinAmount();
        Double maxAmount = (search.getMaxAmount() == null) ? Double.MAX_VALUE : search.getMaxAmount();
        return auctionRepository.search(motCle, minDate, maxDate, minAmount, maxAmount);
    }

    public List<Auction> findByIdClient(int clientId) {
        return auctionRepository.findByClientId(clientId);
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean betOn(AuctionBet auctionBet) throws Exception {
        Auction auction = auctionRepository.findById(auctionBet.getAuctionId()).get();
        if(auction.getClient() == auctionBet.getClient()) {
            throw new Exception("You can't bet on your own auction");
        }
        if (auction.getAmountMin() > auctionBet.getAmount()) {
            throw new Exception("Amount is too low");
        }
        AuctionBet lastAuctionBet = auction.retrieveLastAuctionBet();
        if (lastAuctionBet != null && lastAuctionBet.getAmount() > auctionBet.getAmount()) {
            throw new Exception("Amount is too low");
        }
        if (lastAuctionBet != null) {
            balanceService.creditAccount(lastAuctionBet.getClient().getId(), lastAuctionBet.getAmount());
            // debloquer
            balanceService.saveTransaction(lastAuctionBet.getClient().getId(), lastAuctionBet.getAmount(),
                   auctionBet.getDateBet(), 20, auctionBet.getAuctionId());
        }
        balanceService.debitAccount(auctionBet.getClient().getId(), auctionBet.getAmount()*(-1));
        // bloquer
        balanceService.saveTransaction(auctionBet.getClient().getId(), auctionBet.getAmount(),
                auctionBet.getDateBet() , 10, auctionBet.getAuctionId());
        auctionBetRepo.save(auctionBet);
        return true;
    }
    
    public List<Auction> findCurrentAuctions() {
        return auctionRepository.findCurrentAuctions();
    }

    public Auction findById(int id) {
        return auctionRepository.findById(id).get();
    }

    public Auction save(Auction auction) {
        String reference = "";
        auction.setEndDate(new Timestamp(auction.getDepositoryDate().getTime() + auction.getDuration().longValue()));
        CategoryDetails categoryDetails = categoryDetailsRepository.findById(auction.getCategoryDetails().getId()).get();
        Category category = categoryRepository.findById(categoryDetails.getCategoryId()).get();
        auction.setComission(category.getComission());
        auction.setReference(ls.generateToken().substring(0, 10));
        auctionRepository.save(auction);
       
        auctionRepository.save(auction);
        return auction;
    }
}
