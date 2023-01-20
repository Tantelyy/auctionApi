package com.auction.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.Client;
import com.auction.model.balance.Balance;
import com.auction.model.balance.BalanceLoadRequest;
import com.auction.model.balance.BalanceState;
import com.auction.model.transaction.TransactionDescription;
import com.auction.model.transaction.TransactionHistory;
import com.auction.repository.ClientRepository;
import com.auction.repository.TransactionHistoryRepository;
import com.auction.repository.auction.AuctionRepository;
import com.auction.repository.balance.BalanceLRRepository;
import com.auction.repository.balance.BalanceRepository;
import com.auction.repository.balance.BalanceStateRepository;

@Service
public class BalanceService {
    @Autowired
    private BalanceLRRepository balanceLRRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BalanceStateRepository balanceStateRepository;

    public List<BalanceLoadRequest> getAllBLRequest() {
        return balanceLRRepository.findAll();
    }

    // State 10 = refuse
    // State 20 = en cours
    // State 30 = accepté
    @Transactional(rollbackOn = Exception.class)
    public boolean treatBalanceRequest(Integer id, Integer state) {
        BalanceState balanceState = new BalanceState();
        balanceState.setId(state);
        Date now = Date.valueOf(LocalDate.now());

        BalanceLoadRequest balanceLR = balanceLRRepository.findById(id).get();
        balanceLR.setState(balanceState);
        balanceLR.setTreatmentDate(now);
        // update balanceLR date and state
        balanceLRRepository.save(balanceLR);

        // 30 accepte demande
        if (state == 30) {
            saveTransaction(balanceLR.getClient().getId(), balanceLR.getAmount(), now, 30);
            creditAccount(balanceLR.getClient().getId(), balanceLR.getAmount());
        }
        return true;
    }

    public boolean creditAccount(Integer clientId, Double amount) {
        Balance balance = new Balance();
        balance.setClientId(clientId);
        balance.setAmount(amount);
        balanceRepository.save(balance);
        return true;
    }

    public boolean debitAccount(Integer clientId, Double amount) {
        Balance balance = new Balance();
        balance.setClientId(clientId);
        balance.setAmount(amount * -1);
        balanceRepository.save(balance);
        return true;
    }

    public boolean saveTransaction(Integer clientId, Double amount, Date transactionDate, Integer transactionId) {
        return saveTransaction(clientId, amount, transactionDate, transactionId, null);
    }

    // 10 | Bloquer sur un enchere
    // 20 | Debloquer sur un enchere
    // 30 | Recharger
    public boolean saveTransaction(Integer clientId, Double amount, Date transactionDate, Integer transactionId, Integer auctionId){
        TransactionHistory transactionHistory = new TransactionHistory(transactionId, amount, transactionDate);
        transactionHistory.setClient(clientRepository.findById(clientId).get());
        if(auctionId != null){
            transactionHistory.setAuction(auctionRepository.findById(auctionId).get());
        }
        transactionHistoryRepository.save(transactionHistory);
        return true;
    }

    public boolean reloadAccount(BalanceLoadRequest balanceLoadRequest) {
        BalanceLoadRequest newBalenceLoadRequest = new BalanceLoadRequest();
        newBalenceLoadRequest.setClient(balanceLoadRequest.getClient());
        newBalenceLoadRequest.setAmount(balanceLoadRequest.getAmount());
        newBalenceLoadRequest.setRequestDate(balanceLoadRequest.getRequestDate());
        newBalenceLoadRequest.setContact(balanceLoadRequest.getContact());
        newBalenceLoadRequest.setState(balanceStateRepository.findById(20).get());

        balanceLRRepository.save(newBalenceLoadRequest);

        return true;
    }
}
