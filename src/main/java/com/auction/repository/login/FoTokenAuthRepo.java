package com.auction.repository.login;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auction.model.login.FoTokenAuth;

public interface FoTokenAuthRepo extends JpaRepository<FoTokenAuth,Integer>{

    @Query(value = "select * from fo_token_auth where client_id=?1 and token=?2 and expiration_date>?3",nativeQuery = true)
    public List<FoTokenAuth> getClientToken(Integer id,String token, Date now);

    @Query(value="delete from fo_token_auth where client_id=?1",nativeQuery = true)
    public void deleteAllToken(Integer id);
}
