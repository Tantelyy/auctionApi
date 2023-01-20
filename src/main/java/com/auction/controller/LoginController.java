package com.auction.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.error.UserNotFoundException;
import com.auction.model.common.Data;
import com.auction.model.login.Administrator;
import com.auction.model.login.Connexion;
import com.auction.model.login.Login;
import com.auction.service.login.AdministratorService;
import com.auction.service.login.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    protected AdministratorService as;
    @Autowired
    protected ClientService cs;
    
    @PostMapping
    public Data login(@RequestBody Connexion con) throws UserNotFoundException{
        try{
           Login login = cs.getConnected(con.getEmail(),con.getPassword());
            if(login==null){
                throw new UserNotFoundException("Pas trouvé");
            }
            return new Data(cs.generateToken(login));
        }catch(Exception ex){
            Administrator admin=as.getConnected(con.getEmail(),con.getPassword());
            if(admin==null){
                throw new UserNotFoundException("Pas trouvé");
            }
            return new Data(as.generateToken(admin));
        }
    }
}
