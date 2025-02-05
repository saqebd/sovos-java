/**
 * Copyright (c) 2018 by Sovos Compliance
 */
package com.sovos.coupa.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ApiInformation {

    private String clientUnit;
    private String hmacKey;
    private String url;
    private String password;
    private String username;

    public String getClientUnit() {

        return clientUnit;
    }

    public void setClientUnit(String clientUnit) {

        this.clientUnit = clientUnit;
    }

    public String getHmacKey() {

        return hmacKey;
    }

    public void setHmacKey(String hmacKey) {

        this.hmacKey = hmacKey;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }
}
