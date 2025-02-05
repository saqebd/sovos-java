package com.sovos.coupa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class IAMConfig {

    @Value("${iam.url}")
    String iamServiceUrl;

    @Value("${iam.timeout.baseInMilliseconds:5000}")
    int iamTimeoutBaseInMilliseconds;

    public String getIamServiceUrl() {
        return iamServiceUrl;
    }

    public void setIamServiceUrl(String iamServiceUrl) {
        this.iamServiceUrl = iamServiceUrl;
    }

    public int getIamTimeoutBaseInMilliseconds() {
        return iamTimeoutBaseInMilliseconds;
    }

    public void setIamTimeoutBaseInMilliseconds(int iamTimeoutBaseInMilliseconds) {
        this.iamTimeoutBaseInMilliseconds = iamTimeoutBaseInMilliseconds;
    }
}
