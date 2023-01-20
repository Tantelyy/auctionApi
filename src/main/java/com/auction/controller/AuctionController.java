package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auction.error.AuthenticationException;
import com.auction.model.Client;
import com.auction.model.auction.AuctionBet;
import com.auction.model.auction.Auction;
import com.auction.repository.auction.AuctionSearch;
import com.auction.service.AuctionService;
import com.auction.service.login.FoTokenService;

@RestController
@CrossOrigin
public class AuctionController {

    @Autowired
    AuctionService service;

    @Autowired
    protected FoTokenService fs;

    @GetMapping("/auction/search")
    public ResponseEntity<Object> searchAuction(@RequestBody AuctionSearch auctionSearch) {
        return new ResponseEntity<>(service.search(auctionSearch), HttpStatus.OK);
    }

    @PostMapping("/auction/{id}/bet")
    public ResponseEntity<Object> betOn(@PathVariable Integer id, @RequestParam String token,
            @RequestParam Integer clientId, @RequestBody AuctionBet auctionBet) throws AuthenticationException {
        boolean controlOne = fs.checkTokenClient(token, id);
        if (controlOne) {
            throw new AuthenticationException("Pas connecté");
        }
        // TODO implement token verification
        auctionBet.setAuctionId(id);
        Client client = new Client();
        client.setId(clientId);
        auctionBet.setClient(client);
        try {
            return new ResponseEntity<>(service.betOn(auctionBet), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auctions")
    public ResponseEntity<Object> findCurrentAuctions() {
        return new ResponseEntity<>(service.findCurrentAuctions(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}/auction")
    public ResponseEntity<Object> findByClientid(@PathVariable int clientId) {
        return new ResponseEntity<>(service.findByIdClient(clientId), HttpStatus.OK);
    }

    @GetMapping("/auction/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id)
            throws AuthenticationException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/auction")
    public ResponseEntity<Object> save(@RequestBody Auction auction, @RequestParam Integer id,
            @RequestParam String token) throws AuthenticationException {
        boolean controlOne = fs.checkTokenClient(token, id);
        if (controlOne == false) {
            throw new AuthenticationException("Pas connecté");
        }
        return new ResponseEntity<>(service.save(auction), HttpStatus.OK);
    }

}
