package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GtdRequest {

    @JsonProperty("isAudit")
    private String isAudit;
    @JsonProperty("rsltLvl")
    private String rsltLvl;
    @JsonProperty("trnId")
    private String trnId;
    @JsonProperty("trnSrc")
    private String trnSrc;
    @JsonProperty("trnDocNum")
    private String trnDocNum;
    @JsonProperty("discnts")
    @SerializedName("discnts")
    private Map<Integer, BigDecimal> discnts;
    @JsonProperty("custAttrbs")
    @SerializedName("custAttrbs")
    private Map<String, String> customAttributes;
    @JsonProperty("docDt")
    private String docDt;
    @JsonProperty("currn")
    private String currn;
    @JsonProperty("txCalcTp")
    private String txCalcTp;
    @JsonProperty("dlvrAmt")
    private String dlvrAmt;
    @JsonProperty("usrname")
    private String usrname;
    @JsonProperty("pswrd")
    private String pswrd;
    @JsonProperty("lines")
    private List<LineItemGtdRequest> lines;


    public String getIsAudit() {

        return isAudit;
    }

    public void setIsAudit(String isAudit) {

        this.isAudit = isAudit;
    }

    public String getRsltLvl() {

        return rsltLvl;
    }

    public void setRsltLvl(String rsltLvl) {

        this.rsltLvl = rsltLvl;
    }

    public String getTrnId() {

        return trnId;
    }

    public void setTrnId(String trnId) {

        this.trnId = trnId;
    }

    public String getTrnSrc() {

        return trnSrc;
    }

    public void setTrnSrc(String trnSrc) {

        this.trnSrc = trnSrc;
    }

    public String getTrnDocNum() {

        return trnDocNum;
    }

    public void setTrnDocNum(String trnDocNum) {
        this.trnDocNum = trnDocNum;
    }

    public Map<Integer, BigDecimal> getDiscnts() {
        return this.discnts;
    }

    public void setDiscnts(Map<Integer, BigDecimal> discnts) {
        this.discnts = discnts;
    }

    public Map<String, String> getCustomAttributes() {
        return this.customAttributes;
    }

    public void setCustomAttributes(Map<String, String> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public String getDocDt() {

        return docDt;
    }

    public void setDocDt(String docDt) {
        this.docDt = docDt;
    }

    public String getCurrn() {

        return currn;
    }

    public void setCurrn(String currn) {

        this.currn = currn;
    }

    public String getTxCalcTp() {

        return txCalcTp;
    }

    public void setTxCalcTp(String txCalcTp) {

        this.txCalcTp = txCalcTp;
    }

    public String getUsrname() {

        return usrname;
    }

    public void setUsrname(String usrname) {

        this.usrname = usrname;
    }

    public String getPswrd() {

        return pswrd;
    }

    public void setPswrd(String pswrd) {

        this.pswrd = pswrd;
    }

    public List<LineItemGtdRequest> getLines() {
        return lines;
    }

    public void setLines(List<LineItemGtdRequest> lines) {
        this.lines = lines;
    }

    public String getDlvrAmt() {
        return dlvrAmt;
    }

    public void setDlvrAmt(String dlvrAmt) {
        this.dlvrAmt = dlvrAmt;
    }
}
