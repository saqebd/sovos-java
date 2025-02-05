package com.sovos.coupa.service;


import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sovos.coupa.controller.dto.AccountPropertyResponse;
import com.sovos.coupa.controller.dto.AccountResponse;
import com.sovos.coupa.domain.Account;

import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.ErrorCodes;
import com.sovos.coupa.utility.CoupaRequestContext;
import com.sovos.platform.ServiceExternalException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;


@Service
public class AccountService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    AccountCacheService accountCacheService;

    @Autowired
    CoupaRequestContext requestContext;


    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final long UNIT_TEST_MAX_LINES = 100;
    private static final long DEFAULT_RETENTION_PERIOD = 90;

    public AccountResponse getAccountPropertyResponse(Account account) throws IOException {

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(account.getId());
        accountResponse.setTransactionDate(new Date().toString());
        accountResponse.setMerchantId(account.getMerchantId());
        accountResponse.setUserId(account.getUserId());
        accountResponse.setEmail(account.getEmail());
        accountResponse.setPartnerId(account.getPartnerId());
        accountResponse.setStatus(account.getStatus());
        accountResponse.setType(account.getType());
        accountResponse.setSecretKey(account.getSecretKey());
        accountResponse.setCassInputEnabled(account.isCassInputEnabled());
        accountResponse.setCassOutputEnabled(account.isCassOutputEnabled());
        accountResponse.setApiInformation(account.getApiInformation());

        if (account.getProperties().isEmpty()) {
            //  This only happens junits/mockito.
            //  This nothing to be proud of.
            accountResponse.setMaxLinesPerInvoice(UNIT_TEST_MAX_LINES);
            accountResponse.setRetentionPeriod(DEFAULT_RETENTION_PERIOD);
            accountResponse.setQuotePurgeEnable(true);
            if (LOGGER.isDebugEnabled()) {
                String message = String.format("Reading property file from unit test. max-lines: '%s', retention-period: '%s', purge: '%s'", UNIT_TEST_MAX_LINES, DEFAULT_RETENTION_PERIOD, "true");
                LOGGER.debug(Constants.GENERAL_LOG_FORMAT, escapeJava(requestContext.getCorrelationId()), escapeJava(message));
            }

        } else if (account.getProperties().size() == 1) {
            if (LOGGER.isDebugEnabled()) {
                String payload = account.getProperties().get(0).getJsonBlob();
                String message = "Reading property file from account.";
                LOGGER.debug(Constants.PAYLOAD_LOG_FORMAT, escapeJava(requestContext.getCorrelationId()), escapeJava(message), escapeJava(payload));
            }
            AccountPropertyResponse accountProperty = objectMapper.readValue(
                    account.getProperties().get(0).getJsonBlob(), AccountPropertyResponse.class);
            accountResponse.setMaxLinesPerInvoice(accountProperty.getMaxLinesPerInvoice());
            accountResponse.setRetentionPeriod(accountProperty.getRetentionPeriod());
            accountResponse.setQuotePurgeEnable(accountProperty.getQuotePurgeEnable());
        } else {
            /**
             * Until or If we allow multiple properties per account, we will
             * need to remove this.
             */
            throw new ServiceExternalException(
                    String.format(ErrorCodes.ACCOUNT_PROPERTY_CARDINALITY_FORMAT.getMessage(), account.getId()),
                    ErrorCodes.ACCOUNT_PROPERTY_CARDINALITY_FORMAT.getCode());
        }

        return accountResponse;
    }

}

