package com.sovos.coupa.config;

import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.domain.Solution;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sovos.coupa.domain.ApiInformation;
import com.sovos.platform.security.CoupaUser;

//TODO: this class should not last long, this stuff should not be hard-coded. Do not need to be highly tested
@Configuration
public class ConfigurationService {

    @Value("${gtd.hmackey:notused}")
    private String hmacKey;
    @Value("${gtd.password:notused}")
    private String password;

    public ConfigurationService() {

    }

    public ApiInformation getApiInformation() {
        ApiInformation apiInformation = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null && context.getAuthentication().getDetails() != null) {
            CoupaUser user = (CoupaUser) context.getAuthentication().getDetails();
            CoupaAccount account = user.getAccount();
            for (Solution solution : account.getSolutions()) {
                apiInformation = new ApiInformation();
                apiInformation.setUsername(solution.getGtdUsername());
                apiInformation.setClientUnit(solution.getGtdOrgCode());
                apiInformation.setPassword(password);
                apiInformation.setHmacKey(hmacKey);
            }
        }
        return apiInformation;
    }

}

