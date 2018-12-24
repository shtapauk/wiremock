package com.wiremock.rest.authentication;

public abstract class Authentication implements IAuthentication {

    private String type;
    private String value;

    protected Authentication(String type, String value){
        this.type=type;
        this.value=value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
