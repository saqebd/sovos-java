/**
 * Copyright (c) 2021 by Sovos Compliance
 */
package com.sovos.coupa.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountPropertyResponse {

    private long maxLinesPerInvoice;
    private Long retentionPeriod;
    private boolean quotePurgeEnable;

    public long getMaxLinesPerInvoice() {

        return maxLinesPerInvoice;
    }

    public void setMaxLinesPerInvoice(long maxLinesPerInvoice) {

        this.maxLinesPerInvoice = maxLinesPerInvoice;
    }

    public Long getRetentionPeriod() {

        return retentionPeriod;
    }

    public void setRetentionPeriod(Long retentionPeriod) {

        this.retentionPeriod = retentionPeriod;
    }

    public boolean getQuotePurgeEnable() {

        return quotePurgeEnable;
    }

    public void setQuotePurgeEnable(boolean quotePurgeEnable) {

        this.quotePurgeEnable = quotePurgeEnable;
    }


}
