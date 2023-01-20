package com.auction.repository.login;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.model.login.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    Login findByEmailAndPwd(String email, String pwd);
}
