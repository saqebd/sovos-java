package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GtdResponse {

    @JsonProperty("trnDocNum")
    private String trnDocNum;
    @JsonProperty("txwTrnDocId")
    private String txwTrnDocId;
    @JsonProperty("txAmt")
    private String txAmt;
    @JsonProperty("lnRslts")
    private List<LineItemGtdResponse> lnRslts;


    public String getTrnDocNum() {
        return trnDocNum;
    }

    public void setTrnDocNum(String trnDocNum) {
        this.trnDocNum = trnDocNum;
    }

    public String getTxwTrnDocId() {
        return txwTrnDocId;
    }

    public void setTxwTrnDocId(String txwTrnDocId) {
        this.txwTrnDocId = txwTrnDocId;
    }

    public String getTxAmt() {
        return txAmt;
    }

    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    public List<LineItemGtdResponse> getLnRslts() {
        return lnRslts;
    }

    public void setLnRslts(List<LineItemGtdResponse> lnRslts) {
        this.lnRslts = lnRslts;
    }
}
