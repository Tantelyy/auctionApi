package com.auction.model.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class LoginInformation extends HasId{

    @Column
    private String email;

    @Column
    private String password;
}
