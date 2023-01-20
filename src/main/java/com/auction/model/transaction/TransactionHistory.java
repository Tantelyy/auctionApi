package com.auction.model.transaction;

import java.util.Date;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.model.Client;
import com.auction.model.auction.Auction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("transaction_history")
public class TransactionHistory {
    @Id
    private ObjectId id;
    private String transactionName;
    private String transactionType;
    private Client client;
    private Auction auction;
    private Double amount;
    private Date dateTransaction;

    public TransactionHistory(Integer transactionId, Double amount, Date dateTransanction){
        if(transactionId == 10){
            transactionName = "Bloquer sur un enchere";
            transactionType = "Sortie";
        }
        if(transactionId == 20){
            transactionName = "Debloquer sur un enchere";
            transactionType = "Entre";
        }
        if(transactionId == 10){
            transactionName = "Recharger";
            transactionType = "Entre";
        }
        this.amount = amount;
        this.dateTransaction = dateTransanction;
    }

    public TransactionHistory() {
    }

    
}
