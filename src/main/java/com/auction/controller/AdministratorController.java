package com.auction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.auction.error.AuthenticationException;
import com.auction.error.AuthorizationException;
import com.auction.model.login.Administrator;
import com.auction.service.login.AdministratorService;
import com.auction.service.login.BoTokenService;
import com.auction.service.login.RootService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    AdministratorService as;
    @Autowired
    BoTokenService bs;
    @Autowired
    RootService rs;

    @PostMapping
    public void insertAdministrator(@RequestParam String token,@RequestParam Integer id,@RequestBody Administrator admin) throws AuthenticationException, AuthorizationException{
        boolean controlOne=bs.checkTokenAdmin(token, id);
        boolean controlTwo=rs.isRoot(id);
        if(controlOne){
            throw new AuthenticationException("Pas connecté");
        }
        if(!controlTwo){
            throw new AuthorizationException("Pas autorisé");
        }
        as.insertAdministrator(admin);
    }

    @PutMapping("/{id}")
    public void updateAdministrator(@PathVariable("id") Integer id,@RequestParam Integer idO,@RequestParam String token,@RequestBody Administrator admin) throws AuthorizationException, AuthenticationException{
        if(id==idO){
            boolean controlOne=bs.checkTokenAdmin(token, idO);
            if(controlOne){
                throw new AuthenticationException("Pas connecté");
            }
            as.updateAdministrator(id, admin);
        }else{
            throw new AuthorizationException("Pas autorisé");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrator(@PathVariable("id") Integer id,@RequestParam Integer idO,@RequestParam String token) throws AuthenticationException, AuthorizationException{
        boolean controlOne=bs.checkTokenAdmin(token, idO);
        boolean controlTwo=rs.isRoot(idO);
        if(controlOne){
            throw new AuthenticationException("Pas connecté");
        }
        if(!controlTwo){
            throw new AuthorizationException("Pas autorisé");
        }
        as.deleteAdministrator(id);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable("id") Integer id,@RequestParam Integer idO,@RequestParam String token) throws AuthenticationException{
        boolean controlOne=bs.checkTokenAdmin(token, idO);
        if(controlOne){
            throw new AuthenticationException("Pas connecté");
        }
        as.deleteClient(id);
    }

    @GetMapping("/dashboard")
    //tableau de bord
    public ResponseEntity<Object> getDashboard(@RequestParam String token,@RequestParam Integer id) throws AuthenticationException{
        boolean controlOne=bs.checkTokenAdmin(token, id);
        if(controlOne == false){
            throw new AuthenticationException("Pas connecté");
        }
        return new ResponseEntity<>(as.getDashboard(), HttpStatus.OK);
    }

}
