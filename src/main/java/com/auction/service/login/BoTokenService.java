package com.auction.service.login;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.login.BoTokenAuth;
import com.auction.repository.login.BoTokenAuthRepo;

@Service
public class BoTokenService {
    @Autowired
    protected BoTokenAuthRepo ba;

    public boolean checkTokenAdmin(String token,Integer id){
        List<BoTokenAuth> bt=ba.getAdminToken(id, token,new Date());
        if(bt.size()>=1){
            return true;
        }
        return false;
    }
}
