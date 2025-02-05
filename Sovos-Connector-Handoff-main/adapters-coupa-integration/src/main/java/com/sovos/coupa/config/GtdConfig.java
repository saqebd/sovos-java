package com.sovos.coupa.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class GtdConfig {

    //  Number of seconds to wait for a GTD response.
    @Value("${gtd.backoffPeriodInMilliseconds:50}")
    int backoffPeriodMilliseconds;

    @Value("${gtd.timeout.baseInMilliseconds:500}")
    int gtdTimeoutBaseInMilliseconds;

    @Value("${gtd.timeout.lineScalarInMilliseconds:50}")
    int gtdTimeoutLineScalarInMilliseconds;

    @Value("${gtd.timeout.auditScalarInMilliseconds:2}")
    int gtdTimeoutAuditScalarInMilliseconds;

    @Value("${gtd.address.street.truncate:false}")
    boolean isAddressStreetTruncateEnabled;

    @Value("${gtd.address.street.truncate.size:100}")
    int addressStreetTruncateSize;

    //  Number of retries
    @Value("${gtd.retryCount:2}")
    int retryCount = 2;

    @Value("${gtd.url}")
    String gtdUrl;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getBackoffPeriodMilliseconds() {
        return backoffPeriodMilliseconds;
    }

    public void setBackoffPeriodMilliseconds(int backoffPeriodMilliseconds) {
        this.backoffPeriodMilliseconds = backoffPeriodMilliseconds;
    }

    public int getGtdTimeoutBaseInMilliseconds() {
        return gtdTimeoutBaseInMilliseconds;
    }

    public void setGtdTimeoutBaseInMilliseconds(int gtdTimeoutBaseInMilliseconds) {
        this.gtdTimeoutBaseInMilliseconds = gtdTimeoutBaseInMilliseconds;
    }

    public int getGtdTimeoutLineScalarInMilliseconds() {
        return gtdTimeoutLineScalarInMilliseconds;
    }

    public void setGtdTimeoutLineScalarInMilliseconds(int gtdTimeoutLineScalarInMilliseconds) {
        this.gtdTimeoutLineScalarInMilliseconds = gtdTimeoutLineScalarInMilliseconds;
    }

    public int getGtdTimeoutAuditScalarInMilliseconds() {
        return gtdTimeoutAuditScalarInMilliseconds;
    }

    public void setGtdTimeoutAuditScalarInMilliseconds(int gtdTimeoutAuditScalarInMilliseconds) {
        this.gtdTimeoutAuditScalarInMilliseconds = gtdTimeoutAuditScalarInMilliseconds;
    }

    public boolean isAddressStreetTruncateEnabled() {
        return isAddressStreetTruncateEnabled;
    }

    public void setAddressStreetTruncateEnabled(boolean isAddressStreetTruncate) {
        this.isAddressStreetTruncateEnabled = isAddressStreetTruncate;
    }

    public int getAddressStreetTruncateSize() {
        return addressStreetTruncateSize;
    }

    public void setAddressStreetTruncateSize(int addressStreetTruncateSize) {
        this.addressStreetTruncateSize = addressStreetTruncateSize;
    }

    public String getGtdUrl() {
        return gtdUrl;
    }

    public void setGtdUrl(String gtdUrl) {
        this.gtdUrl = gtdUrl;
    }
}
