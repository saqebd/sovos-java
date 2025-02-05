package com.sovos.coupa.domain;


public class SolutionReference {

    private String id;
    private String name;
    private String status;
    private String abbreviation;
    private String url = null;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}