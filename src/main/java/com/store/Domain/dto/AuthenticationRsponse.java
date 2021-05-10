package com.store.Domain.dto;

public class AuthenticationRsponse {
    private String jwt;

    public AuthenticationRsponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
