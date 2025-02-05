package com.sovos.platform.security;

public class AuthenticationDto {

    private String userId;
    private String mechantId;
    private String hash;

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getMechantId() {

        return mechantId;
    }

    public void setMechantId(String mechantId) {

        this.mechantId = mechantId;
    }

    public String getHash() {

        return hash;
    }

    public void setHash(String hash) {

        this.hash = hash;
    }
}

