package com.wiremock.pojo;

import wiremock.com.fasterxml.jackson.annotation.JsonIgnore;
import wiremock.com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEntity {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonIgnore
    private Object address;
    @JsonIgnore
    private Object phone;
    @JsonIgnore
    private Object website;
    @JsonIgnore
    private Object company;

    @JsonProperty("email")
    public void setAddress(Object address) {
        this.address = address;
    }

    @JsonProperty("email")
    public void setPhone(Object phone) {
        this.phone = phone;
    }

    @JsonProperty("email")
    public void setWebsite(Object website) {
        this.website = website;
    }

    @JsonProperty("email")
    public void setCompany(Object company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone=" + phone +
                ", website=" + website +
                ", company=" + company +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
