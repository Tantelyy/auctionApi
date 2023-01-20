package com.auction.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.auction.model.transaction.TransactionHistory;

public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory, Integer> {
    
}
