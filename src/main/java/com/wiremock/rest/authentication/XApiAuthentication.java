package com.wiremock.rest.authentication;

public class XApiAuthentication extends Authentication {
    private static final String HEADER_TYPE = "X-ApiKey";

    public XApiAuthentication(String value) {
        super(HEADER_TYPE, value);
    }
}
