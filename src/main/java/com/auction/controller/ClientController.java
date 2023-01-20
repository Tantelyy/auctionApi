package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auction.error.AuthenticationException;
import com.auction.model.Client;
import com.auction.model.ClientInscription;
import com.auction.repository.login.FoTokenAuthRepo;
import com.auction.service.login.ClientService;
import com.auction.service.login.FoTokenService;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    @Autowired
    protected ClientService cs;
    @Autowired
    protected FoTokenService fs;
    @Autowired
    protected FoTokenAuthRepo ftap;

    @PostMapping
    public void insertClient(@RequestBody ClientInscription client){
        cs.saveClient(client);
    }

    @PutMapping
    public void updateClient(@RequestParam Integer id,@RequestParam String token,@RequestBody Client client) throws AuthenticationException{
        boolean controlOne=fs.checkTokenClient(token, id);
        if(controlOne){
            throw new AuthenticationException("Pas connecté");
        }
        cs.updateClient(id, client);
    }

    @DeleteMapping
    public void deleteClient(@RequestParam Integer id,@RequestParam String token) throws AuthenticationException{
        boolean controlOne=fs.checkTokenClient(token, id);
        if(controlOne){
            throw new AuthenticationException("Pas connecté");
        }
        ftap.deleteAllToken(id);
        cs.deleteClient(id);
    }

}
