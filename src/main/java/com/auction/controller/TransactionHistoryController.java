package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.auction.service.TransactionHistoryService;

@CrossOrigin
@RestController
public class TransactionHistoryController {
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Object> findByClientId(@PathVariable int id) {
        return new ResponseEntity<>(transactionHistoryService.findByClientId(id), HttpStatus.OK);
    }
}
