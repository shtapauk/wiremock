package com.wiremock.rest.authentication;

public class PmAuthenticationToken extends Authentication {
    private static final String HEADER_TYPE = "PmAuthenticationToken";

    public PmAuthenticationToken(String value) {
        super(HEADER_TYPE, value);
    }
}
