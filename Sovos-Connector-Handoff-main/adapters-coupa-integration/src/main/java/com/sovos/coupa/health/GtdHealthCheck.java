package com.sovos.coupa.health;

import com.sovos.coupa.service.RestService;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.RecoverableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;


@Component
public class GtdHealthCheck extends AbstractHealthIndicator {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RestService restService;

    @Override
    protected void doHealthCheck(Health.Builder builder) {

        int gtdHealthStatus = 0;
        try {
            gtdHealthStatus = restService.getGtdHealthStatus();
        } catch (RecoverableException e) {
            LOGGER.error(String.format(Constants.GENERAL_LOG_FORMAT, null, e.getMessage()));
        }
        if (gtdHealthStatus == 200) {
            builder.up();
        } else {
            builder.down();
        }
    }
}
