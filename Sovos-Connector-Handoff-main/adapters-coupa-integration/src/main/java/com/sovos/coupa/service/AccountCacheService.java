package com.sovos.coupa.service;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import java.lang.invoke.MethodHandles;

import com.sovos.coupa.config.AccountCache;
import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.utility.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountCacheService {

    @Autowired
    private AccountCache cache;

    @Autowired
    private S1IamService s1IamService;

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public CoupaAccount getAccount(String correlationId, String s1AccountId) {

        CoupaAccount cachedAccount = cache.get(s1AccountId);
        if (cachedAccount == null) {
            cachedAccount = s1IamService.getAccount(s1AccountId);
            cache.put(s1AccountId, cachedAccount);
            if (LOGGER.isDebugEnabled()) {
                String message = String.format("Account not found in cache with id: '%s'", s1AccountId);
                LOGGER.debug(Constants.GENERAL_LOG_FORMAT, escapeJava(correlationId), escapeJava(message));
            }
        }
        return cachedAccount;
    }
}
