package com.auction.repository.login;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auction.model.login.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator,Integer>{
    @Query( value = "select * from administrator where email=?1 and pwd=?2", nativeQuery = true )
    public Administrator findByEmailAndPwd(String email, String password);
}
