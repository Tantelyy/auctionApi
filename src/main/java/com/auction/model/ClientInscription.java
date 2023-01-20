package com.auction.model;

import com.auction.model.login.Login;

public class ClientInscription {
    private Client client;
    private Login login;
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    
}
