package com.wiremock.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.wiremock.rest.authentication.Authentication;
import com.wiremock.rest.authentication.BasicAuthentication;
import com.wiremock.rest.authentication.IAuthentication;

import java.util.Map;
import java.util.TreeMap;

public class RestHelper {
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String USER_AGENT_VALUE = "Chrome/69.0.3497.100";
    private static final String USER_AGENT = "User-Agent";
    private  HttpRequest request;

    public RestHelper post(String url, String body){
        request = Unirest.post(url);
        defaultHeaders();
        ((HttpRequestWithBody)request).body(body);

        return this;
    }

    public RestHelper put(String url, String body) throws UnirestException {
        request = Unirest.put(url);
        defaultHeaders();
        ((HttpRequestWithBody)request).body(body);

        return this;
    }

    public RestHelper get(String url) throws UnirestException {
        request = Unirest.get(url);
        defaultHeaders();

        return this;
    }

    public RestHelper withAuthentication(IAuthentication iauth){
        addAuthentication(iauth);

        return this;
    }

    public RestHelper addHeaders(Map<String,String> headers){
        request.headers(headers);

        return this;
    }

    public RestHelper changeContentType(String value){
        request.getHeaders().remove(CONTENT_TYPE);
        request.header(CONTENT_TYPE,value);

        return this;
    }

    public RestHelper addParameters(Map<String, Object> fields) throws UnirestException {
        ((HttpRequestWithBody)request).fields(fields);

        return this;
    }

    public RestHelper addQuery(Map<String, Object> queries) throws UnirestException {
        request.queryString(queries);

        return this;
    }

    public JsonNode buildResultAsJsonBody() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = request.asJson();

        return jsonResponse.getBody();
    }

    public HttpResponse<JsonNode> buildResultAsJson() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = request.asJson();

        return jsonResponse;
    }

    public HttpResponse<String> buildResultAsString() throws UnirestException {
        HttpResponse<String> response = request.asString();

        return response;
    }

    private void addAuthentication(IAuthentication iauth){
        if (iauth instanceof BasicAuthentication) {
            BasicAuthentication bAuth = (BasicAuthentication) iauth;
            request.basicAuth(bAuth.getName(), bAuth.getPassword());
        }
        if (iauth instanceof Authentication) {
            Authentication auth = (Authentication) iauth;
            request.header(auth.getType(), auth.getValue());
        }
    }

    private void defaultHeaders(){
        Map headers = new TreeMap<String, String>();
        headers.put(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        headers.put(USER_AGENT,USER_AGENT_VALUE);

        request.headers(headers);
    }
}