package com.auction.model.login;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.auction.model.common.HasId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Login extends HasId {
    @Column
    private Integer clientId;
    @Column
    private String email;
    @Column
    private String pwd;
}
