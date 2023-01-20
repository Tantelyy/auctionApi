package com.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auction.model.Client;


public interface ClientRepository  extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c limit 1 order by c.id desc")
    public Client findLast();
}
