package com.abdulrhman.springboot.api.security;

public class jwtResponse {

    private String Token;

    public jwtResponse(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
