package com.wiremock.rest.authentication;

public class BearerAuthorization extends Authentication {
    private static final String HEADER_TYPE = "Authorization";

    public BearerAuthorization(String value) {
        super(HEADER_TYPE, String.format("Bearer %s", value));
    }
}
