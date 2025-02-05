package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.sovos.integrations.gtd.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

public class LineItemGtdRequest {

    @Schema
    private String lnItmNum;

    public String getLnItmNum() {
        return lnItmNum;
    }

    public void setLnItmNum(String lnItmNum) {
        this.lnItmNum = lnItmNum;
    }

    @Schema
    private String orgCd;

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    @JsonProperty("trnTp")
    @SerializedName("trnTp")
    private Integer transactionType;

    public void setTransactionTypeEnum(TransactionType type) {
        this.transactionType = type.getGtdJsonValue();
    }

    public Integer getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    @Schema
    private String grossAmt;

    public String getGrossAmt() {
        return grossAmt;
    }

    public void setGrossAmt(String grossAmt) {
        this.grossAmt = grossAmt;
    }

    @Schema
    private DiscountLineItemGtdRequest discnts;

    public DiscountLineItemGtdRequest getDiscnts() {
        return discnts;
    }

    public void setDiscnts(DiscountLineItemGtdRequest discnts) {
        this.discnts = discnts;
    }

    @Schema
    private String debCredIndr;

    public String getDebCredIndr() {
        return debCredIndr;
    }

    public void setDebCredIndr(String debCredIndr) {
        this.debCredIndr = debCredIndr;
    }

    @Schema
    private String goodSrvCd;

    public String getGoodSrvCd() {
        return goodSrvCd;
    }

    public void setGoodSrvCd(String goodSrvCd) {
        this.goodSrvCd = goodSrvCd;
    }

    @Schema
    private String goodSrvDesc;

    public String getGoodSrvDesc() {
        return goodSrvDesc;
    }

    public void setGoodSrvDesc(String goodSrvDesc) {
        this.goodSrvDesc = goodSrvDesc;
    }

    @Schema
    private String qnty;

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }

    @Schema
    private String sFCity;

    public String getsFCity() {
        return sFCity;
    }

    public void setsFCity(String sFCity) {
        this.sFCity = sFCity;
    }

    @Schema
    private String sFStateProv;

    public String getsFStateProv() {
        return sFStateProv;
    }

    public void setsFStateProv(String sFStateProv) {
        this.sFStateProv = sFStateProv;
    }

    @Schema
    private String sFCountry;

    public String getsFCountry() {
        return sFCountry;
    }

    public void setsFCountry(String sFCountry) {
        this.sFCountry = sFCountry;
    }

    @Schema
    private String sFPstlCd;

    public String getsFPstlCd() {
        return sFPstlCd;
    }

    public void setsFPstlCd(String sFPstlCd) {
        this.sFPstlCd = sFPstlCd;
    }

    @Schema
    private String sFPstlCdExt;

    public String getsFPstlCdExt() {
        return sFPstlCdExt;
    }

    public void setsFPstlCdExt(String sFPstlCdExt) {
        this.sFPstlCdExt = sFPstlCdExt;
    }

    @Schema
    private String sFStNameNum;

    public String getsFStNameNum() {
        return sFStNameNum;
    }

    public void setsFStNameNum(String sFStNameNum) {
        this.sFStNameNum = sFStNameNum;
    }

    @Schema
    private String sFLocationCode;

    public String getsFLocationCode() {
        return sFLocationCode;
    }

    public void setsFLocationCode(String sFLocationCode) {
        this.sFLocationCode = sFLocationCode;
    }

    @Schema
    private String sTCity;

    public String getsTCity() {
        return sTCity;
    }

    public void setsTCity(String sTCity) {
        this.sTCity = sTCity;
    }

    @Schema
    private String sTStateProv;

    public String getsTStateProv() {
        return sTStateProv;
    }

    public void setsTStateProv(String sTStateProv) {
        this.sTStateProv = sTStateProv;
    }

    @Schema
    private String sTCountry;

    public String getsTCountry() {
        return sTCountry;
    }

    public void setsTCountry(String sTCountry) {
        this.sTCountry = sTCountry;
    }

    @Schema
    private String sTPstlCd;

    public String getsTPstlCd() {
        return sTPstlCd;
    }

    public void setsTPstlCd(String sTPstlCd) {
        this.sTPstlCd = sTPstlCd;
    }

    @Schema
    private String sTPstlCdExt;

    public String getsTPstlCdExt() {
        return sTPstlCdExt;
    }

    public void setsTPstlCdExt(String sTPstlCdExt) {
        this.sTPstlCdExt = sTPstlCdExt;
    }

    @Schema
    private String sTStNameNum;

    public String getsTStNameNum() {
        return sTStNameNum;
    }

    public void setsTStNameNum(String sTStNameNum) {
        this.sTStNameNum = sTStNameNum;
    }

    @Schema
    private String sTLocationCode;

    public String getsTLocationCode() {
        return sTLocationCode;
    }

    public void setsTLocationCode(String sTLocationCode) {
        this.sTLocationCode = sTLocationCode;
    }

    @Schema
    private String bTCity;

    public String getbTCity() {
        return bTCity;
    }

    public void setbTCity(String bTCity) {
        this.bTCity = bTCity;
    }

    @Schema
    private String bTStateProv;

    public String getbTStateProv() {
        return bTStateProv;
    }

    public void setbTStateProv(String bTStateProv) {
        this.bTStateProv = bTStateProv;
    }

    @Schema
    private String bTCountry;

    public String getbTCountry() {
        return bTCountry;
    }

    public void setbTCountry(String bTCountry) {
        this.bTCountry = bTCountry;
    }

    @Schema
    private String bTPstlCd;

    public String getbTPstlCd() {
        return bTPstlCd;
    }

    public void setbTPstlCd(String bTPstlCd) {
        this.bTPstlCd = bTPstlCd;
    }

    @Schema
    private String bTPstlCdExt;

    public String getbTPstlCdExt() {
        return bTPstlCdExt;
    }

    public void setbTPstlCdExt(String bTPstlCdExt) {
        this.bTPstlCdExt = bTPstlCdExt;
    }

    @Schema
    private String bTStNameNum;

    public String getbTStNameNum() {
        return bTStNameNum;
    }

    public void setbTStNameNum(String bTStNameNum) {
        this.bTStNameNum = bTStNameNum;
    }

    @Schema
    private String bTLocationCode;

    public String getbTLocationCode() {
        return sTLocationCode;
    }

    public void setbTLocationCode(String bTLocationCode) {
        this.bTLocationCode = bTLocationCode;
    }


    @Schema
    private String custVendName;

    public String getCustVendName() {
        return custVendName;
    }

    public void setCustVendName(String custVendName) {
        this.custVendName = custVendName;
    }

    @Schema
    private String custVendCd;

    public String getCustVendCd() {
        return custVendCd;
    }

    public void setCustVendCd(String custVendCd) {
        this.custVendCd = custVendCd;
    }

    @Schema
    private String dlvrDt;

    public String getDlvrDt() {
        return dlvrDt;
    }

    public void setDlvrDt(String dlvrDt) {
        this.dlvrDt = dlvrDt;
    }

    @Schema
    private CustomFieldsLineItemGtdRequest custAttrbs;

    public CustomFieldsLineItemGtdRequest getCustAttrbs() {
        return custAttrbs;
    }

    public void setCustAttrbs(CustomFieldsLineItemGtdRequest custAttrbs) {
        this.custAttrbs = custAttrbs;
    }
}

