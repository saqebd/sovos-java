package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxLineDetailsGtdResponse {
    @JsonProperty("txAmt")
    private String txAmt;
    @JsonProperty("txRate")
    private String txRate;
    @JsonProperty("txName")
    private String txName;

    public String getTxAmt() {
        return txAmt;
    }

    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    public String getTxRate() {
        return txRate;
    }

    public void setTxRate(String txRate) {
        this.txRate = txRate;
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }
}
