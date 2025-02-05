package com.sovos.coupa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthenticationConfig {

    @Value("${header:Authorization}")
    private String header;
    @Value("${timestamp.header:x-request-date}")
    private String timestampHeader;
    @Value("${valid.range:300}")
    private Long validRange;

    public String getHeader() {
        return header;
    }

    public void setHeader(String tokenHeader) {
        this.header = tokenHeader;
    }

    public String getTimestampHeader() {
        return timestampHeader;
    }

    public void setTimestampHeader(String timestampHeader) {
        this.timestampHeader = timestampHeader;
    }

    public Long getValidRange() {
        return validRange;
    }

    public void setValidRange(Long validRange) {
        this.validRange = validRange;
    }
}
