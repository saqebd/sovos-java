package com.sovos.coupa.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountProperty {

    public static final String STATUS_NONE = "NONE";
    static private ObjectMapper objectMapper = new ObjectMapper();


    private String id;

    //  Currently undefined and null allowed.
    private String status;

    /*
     * effective and expire dates may overlap with other entries.
     */
    //  Null allowed, and currently not used.
    private Timestamp effectiveDate;

    //  Null allowed, and currently not used.
    private Timestamp expiredDate;

    //  Big glob, containing JSON object property/value pairs.
    private String jsonBlob;

    private Account owner;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public Timestamp getEffectiveDate() {

        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {

        this.effectiveDate = effectiveDate;
    }

    public Timestamp getExpiredDate() {

        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {

        this.expiredDate = expiredDate;
    }

    public String getJsonBlob() {

        return jsonBlob;
    }

    public void setJsonBlob(String jsonBlob) {

        this.jsonBlob = jsonBlob;
    }

    public Account getOwner() {

        return this.owner;
    }

    public void setOwner(Account account) {

        this.owner = account;

    }
}
