package com.wiremock.rest.authentication;

public class BasicAuthentication implements IAuthentication {
    private String name;
    private String password;

    public BasicAuthentication(String name, String password){
        this.name = name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
