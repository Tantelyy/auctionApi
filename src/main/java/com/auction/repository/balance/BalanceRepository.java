package com.auction.repository.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.balance.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    public Balance findByClientId(Integer clientId);
}
