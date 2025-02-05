package com.sovos.coupa.controller.dto;

import java.io.Serializable;

import com.sovos.coupa.domain.ApiInformation;

public class TransactionData implements Serializable {

    private static final long serialVersionUID = 3416652486820535077L;

    private String gtdRequest;
    private String gtdResponse;
    private String externalResponse;
    private String externalRequest;
    private String format;
    private String type;

    private transient ApiInformation apiInformation;

    public String getGtdRequest() {
        return gtdRequest;
    }

    public void setGtdRequest(String gtdRequest) {
        this.gtdRequest = gtdRequest;
    }

    public String getGtdResponse() {
        return gtdResponse;
    }

    public void setGtdResponse(String gtdResponse) {
        this.gtdResponse = gtdResponse;
    }

    public String getExternalResponse() {
        return externalResponse;
    }

    public void setExternalResponse(String output) {
        this.externalResponse = output;
    }

    public String getExternalRequest() {
        return externalRequest;
    }

    public void setExternalRequest(String input) {
        this.externalRequest = input;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String outputFormat) {
        this.format = outputFormat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApiInformation(ApiInformation apiInformation) {
        this.apiInformation = apiInformation;
    }

    public ApiInformation getApiInformation() {
        return this.apiInformation;
    }
}
