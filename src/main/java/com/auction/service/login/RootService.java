package com.auction.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.login.Root;
import com.auction.repository.login.RootRepository;

@Service
public class RootService {
    
    @Autowired
    RootRepository rr;

    public boolean isRoot(Integer id){
        List<Root> root=rr.findAll();
        for(int i=0;i<root.size();i++){
            if(root.get(i).getIdRoot()==id){
                return true;
            }
        }
        return false;
    }
}
