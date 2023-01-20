package com.auction.service.login;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.login.FoTokenAuth;
import com.auction.repository.login.FoTokenAuthRepo;

@Service
public class FoTokenService {
    @Autowired 
    protected FoTokenAuthRepo fa;

    public boolean checkTokenClient(String token,Integer id){
        List<FoTokenAuth> listClient=fa.getClientToken(id, token,new Date());
        if(listClient.size()>=1){
            return true;
        }
        return false;
    }
}
