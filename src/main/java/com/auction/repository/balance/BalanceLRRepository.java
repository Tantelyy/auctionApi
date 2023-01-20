package com.auction.repository.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.balance.BalanceLoadRequest;

@Repository
public interface BalanceLRRepository extends JpaRepository<BalanceLoadRequest, Integer> {

}