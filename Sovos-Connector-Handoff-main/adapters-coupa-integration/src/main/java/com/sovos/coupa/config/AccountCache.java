package com.sovos.coupa.config;

import jakarta.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

import com.sovos.coupa.domain.CoupaAccount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class AccountCache {
    private Cache<String, CoupaAccount> accountCache;
    @Value("${coupa.accountCacheExpirationTimeInSeconds:1500}")
    private int accountCacheExpirationTimeInSeconds;

    @Value("${coupa.accountCacheSize:50}")
    private int accountCacheSize;

    public void put(String id, CoupaAccount response) {
        accountCache.put(id, response);
    }

    public CoupaAccount get(String id) {
        return accountCache.getIfPresent(id);
    }

    public void delete(String id) {
        accountCache.invalidate(id);
    }

    @PostConstruct
    public void initCacheStore() {
        accountCache = CacheBuilder.newBuilder().maximumSize(accountCacheSize)
                .expireAfterWrite(accountCacheExpirationTimeInSeconds, TimeUnit.SECONDS)
                .recordStats().build();
    }
}
