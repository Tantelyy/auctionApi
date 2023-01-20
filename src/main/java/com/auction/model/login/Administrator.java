package com.auction.model.login;
import javax.persistence.Entity;
import javax.persistence.Column;
import com.auction.model.common.HasId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Administrator extends HasId{

    @Column
    private String email;

    @Column
    private String pwd;
}
