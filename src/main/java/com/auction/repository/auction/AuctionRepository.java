package com.auction.repository.auction;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auction.model.auction.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    @Query("select a from Auction a where (a.productName like %:motCle% or a.productDescription like %:motCle%) and a.endDate between :minDate and :maxDate and a.amountMin between :minAmount and :maxAmount")
    List<Auction> search(String motCle, Date minDate, Date maxDate, Double minAmount,Double maxAmount);
    
    @Query(value = "SELECT * FROM auction WHERE client_id=?1", nativeQuery = true)
    public List<Auction> findByClientId(int clientId);
    
    @Query(value = "SELECT * FROM v_enchere_en_cours", nativeQuery = true)
    public List<Auction> findCurrentAuctions();
}
