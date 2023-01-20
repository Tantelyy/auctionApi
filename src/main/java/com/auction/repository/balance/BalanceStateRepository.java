package com.auction.repository.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.model.balance.BalanceState;

@Repository
public interface BalanceStateRepository extends JpaRepository<BalanceState, Integer> {

}
