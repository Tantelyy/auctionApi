package com.auction.repository.login;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auction.model.login.BoTokenAuth;

public interface BoTokenAuthRepo extends JpaRepository<BoTokenAuth,Integer>{

    @Query(value = "select * from bo_token_auth where admin_id=?1 and token=?2 and expiration_date>?3", nativeQuery = true)
    public List<BoTokenAuth> getAdminToken(Integer id,String token, Date now);
}
