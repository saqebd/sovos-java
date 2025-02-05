/**
 * Copyright (c) 2021 by Sovos Compliance
 */
package com.sovos.coupa.controller.dto;

import com.sovos.coupa.domain.ApiInformation;

public class AccountResponse extends AccountPropertyResponse {

    public static final long UNIT_TEST_MAX_LINES = 100;

    private String transactionDate;
    private String accountId;
    private String merchantId;
    private String userId;
    private String partnerId;
    private String email;
    private String status;
    private String type;
    private String secretKey;
    private boolean isCassInputEnabled;
    private boolean isCassOutputEnabled;
    private ApiInformation apiInformation;


    public String getTransactionDate() {

        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {

        this.transactionDate = transactionDate;
    }

    public String getAccountId() {

        return accountId;
    }

    public void setAccountId(String accountId) {

        this.accountId = accountId;
    }

    public String getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(String merchantId) {

        this.merchantId = merchantId;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getPartnerId() {

        return partnerId;
    }

    public void setPartnerId(String partnerId) {

        this.partnerId = partnerId;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getSecretKey() {

        return secretKey;
    }

    public void setSecretKey(String secretKey) {

        this.secretKey = secretKey;
    }

    public boolean isCassInputEnabled() {

        return isCassInputEnabled;
    }

    public void setCassInputEnabled(boolean cassInputEnabled) {

        isCassInputEnabled = cassInputEnabled;
    }

    public boolean isCassOutputEnabled() {

        return isCassOutputEnabled;
    }

    public void setCassOutputEnabled(boolean cassOutputEnabled) {

        isCassOutputEnabled = cassOutputEnabled;
    }

    public ApiInformation getApiInformation() {

        return apiInformation;
    }

    public void setApiInformation(ApiInformation apiInformation) {

        this.apiInformation = apiInformation;
    }

    @Override
    public String toString() {

        return "AccountResponse{" + "transactionDate='" + this.getTransactionDate() + '\''
                + ", accountId='" + this.getAccountId() + '\'' + "maxInvoicelines="
                + this.getMaxLinesPerInvoice() + "retentionPeriod=" + this.getRetentionPeriod() + "quotePurgeEnable="
                + this.getQuotePurgeEnable() + '}';
    }
}
