package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auction.model.balance.BalanceLoadRequest;
import com.auction.service.BalanceService;

@RestController
@CrossOrigin
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/balance/load-request")
    public ResponseEntity<Object> getAllBLRequest() {
        return new ResponseEntity<>(balanceService.getAllBLRequest(), HttpStatus.OK);
    }

    @PostMapping("/balance/treat-load-request")
    public boolean treatBalanceRequest(@RequestBody BalanceLoadRequest balanceLoadRequest) {
        return balanceService.treatBalanceRequest(balanceLoadRequest.getId(), balanceLoadRequest.getState().getId());
    }

    @PostMapping("/balance/reload-account")
    public ResponseEntity<Object> createReloadAccount(@RequestBody BalanceLoadRequest balanceLoadRequest) {
        return new ResponseEntity<>(balanceService.reloadAccount(balanceLoadRequest), HttpStatus.OK);
    }

}
