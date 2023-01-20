package com.auction.service.login;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.Client;
import com.auction.model.ClientInscription;
import com.auction.model.login.FoTokenAuth;
import com.auction.model.login.Login;
import com.auction.repository.ClientRepository;
import com.auction.repository.login.FoTokenAuthRepo;
import com.auction.repository.login.LoginRepository;

@Service
public class ClientService {

    @Autowired
    LoginService ls;

    @Autowired
    ClientRepository cr;

    @Autowired
    LoginRepository loginRepo;

    @Autowired
    FoTokenAuthRepo fta;

    public FoTokenAuth generateToken(Login login){
        String token=ls.generateToken();
        Date expiration=new Date();
        int date=expiration.getDate()+2;
        expiration.setDate(date);

        FoTokenAuth fo=new FoTokenAuth();
        fo.setClient(cr.findById(login.getClientId()).get());
        fo.setToken(token);
        fo.setExpirationDate(expiration);
        fta.save(fo);
        return fo;
    }

    public Login getConnected(String email,String password){
        return loginRepo.findByEmailAndPwd(email, password);
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveClient(ClientInscription client){
        Client c=client.getClient();
        c= cr.save(c);
        // c=cr.findLast();
        client.getLogin().setClientId(c.getId());
        loginRepo.save(client.getLogin());
    }

    public void updateClient(Integer id,Client client){
        client.setId(id);
        cr.save(client);
    }

    public void deleteClient(Integer id){
        cr.deleteById(id);
    }

}
