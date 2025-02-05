package com.sovos.coupa.utility;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoupaRequestContext {

    private String requestId;
    private String correlationId;
    private String implementerId;
    private transient Instant startInstant;
    private Long requestElapsedTime;
    private List<Long> aesElapsedTimes;
    private List<Long> gtdElapsedTimes;
    private Map<String, String> metaData;
    private String urlPath;
    private Integer code;
    private String message;
    private String transactionId;
    private transient Instant startAesTime;
    private transient Instant startGtdTime;

    public CoupaRequestContext() {
        this.startInstant = Instant.now();
    }

    public String getRequestId() {

        return requestId;
    }

    public void setRequestId(String requestId) {

        this.requestId = requestId;
    }

    public String getCorrelationId() {

        return correlationId;
    }

    public void setCorrelationId(String correlationId) {

        this.correlationId = correlationId;
    }

    public String getImplementerId() {
        return implementerId;
    }

    public void setImplementerId(String implementerId) {
        this.implementerId = implementerId;
    }

    public Instant getStartInstant() {
        return startInstant;
    }

    public void setStartInstant(Instant startInstant) {
        this.startInstant = startInstant;
    }

    public List<Long> getAesTimes() {
        return aesElapsedTimes;
    }

    public void setAesTimes(List<Long> time) {
        this.aesElapsedTimes = time;
    }

    public void addAesTime(Long time) {
        if (this.aesElapsedTimes == null) {
            this.aesElapsedTimes = new ArrayList<>();
        }
        this.aesElapsedTimes.add(time);
    }

    public List<Long> getGtdTimes() {
        return gtdElapsedTimes;
    }

    public void setGtdTimes(List<Long> time) {
        this.gtdElapsedTimes = time;
    }

    public void addGtdTime(Long time) {
        if (this.gtdElapsedTimes == null) {
            this.gtdElapsedTimes = new ArrayList<>();
        }
        this.gtdElapsedTimes.add(time);
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public void addMetaData(String key, String value) {
        if (this.metaData == null) {
            this.metaData = new HashMap<>();
        }
        this.metaData.put(key, value);
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        this.requestElapsedTime = Instant.now().minusMillis(this.startInstant.toEpochMilli()).toEpochMilli();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }

    public void startAesTime() {
        this.startAesTime = Instant.now();

    }

    public void endAesTime() {
        if (this.startAesTime != null) {
            this.addAesTime(Instant.now().minusMillis(this.startAesTime.toEpochMilli()).toEpochMilli());
            this.startAesTime = null;
        }

    }

    public void startGtdTime() {
        this.startGtdTime = Instant.now();

    }

    public void endGtdTime() {
        if (this.startGtdTime != null) {
            this.addGtdTime(Instant.now().minusMillis(this.startGtdTime.toEpochMilli()).toEpochMilli());
            this.startGtdTime = null;
        }

    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

}
