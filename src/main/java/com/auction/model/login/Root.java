package com.auction.model.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="root")
public class Root{
    
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idroot;

    @Column
    private int identifiantroot;

    public int getIdRoot() {
        return idroot;
    }

    public void setIdRoot(int idroot) {
        this.idroot = idroot;
    }

    public int getIdentifiantroot() {
        return identifiantroot;
    }

    public void setIdentifiantroot(int identifiantroot) {
        this.identifiantroot = identifiantroot;
    }

    
}
