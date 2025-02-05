package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class LineItemGtdResponse {

    @JsonProperty("lnNm")
    private String lnNm;
    @JsonProperty("lnId")
    private String lnId;
    @JsonProperty("txAmt")
    private String txAmt;
    @JsonProperty("grossAmt")
    private String grossAmt;
    @JsonProperty("txRate")
    private String txRate;
    @JsonProperty("jurRslts")
    private List<TaxLineDetailsGtdResponse> jurRslts = new ArrayList<>();


    public String getLnNm() {
        return lnNm;
    }

    public void setLnNm(String lnNm) {
        this.lnNm = lnNm;
    }

    public String getLnId() {
        return lnId;
    }

    public void setLnId(String lnId) {
        this.lnId = lnId;
    }

    public String getTxAmt() {
        return txAmt;
    }

    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    public String getGrossAmt() {
        return grossAmt;
    }

    public void setGrossAmt(String grossAmt) {
        this.grossAmt = grossAmt;
    }

    public String getTxRate() {
        return txRate;
    }

    public void setTxRate(String txRate) {
        this.txRate = txRate;
    }

    public List<TaxLineDetailsGtdResponse> getJurRslts() {
        return jurRslts;
    }

    public void setJurRslts(List<TaxLineDetailsGtdResponse> jurRslts) {
        this.jurRslts = jurRslts;
    }
}