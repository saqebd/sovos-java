package com.sovos.coupa.service;


import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.google.gson.Gson;
import com.sovos.coupa.config.ConfigurationService;
import com.sovos.coupa.controller.dto.GtdRequest;
import com.sovos.coupa.controller.dto.GtdResponse;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.CoupaRequestContext;
import com.sovos.coupa.utility.RecoverableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.invoke.MethodHandles;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.util.Calendar;


@Service
public class CoupaTransactionService {
    public static final String HEADERS_DATE = "Date";
    private static final String CALCULATE_DOC = "calcTax/doc";
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ConfigurationService configuration;

    @Autowired
    private CoupaRequestContext coupaRequestContext;

    public ResponseEntity<GtdResponse> executeEvaluate(GtdRequest request, String url) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String timestamp = DatatypeConverter.printDateTime(Calendar.getInstance());

        try {
            headers.set(HEADERS_DATE, timestamp);
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", getAuthorizationString(
                    "calcTax/doc",
                    timestamp,
                    configuration.getApiInformation().getUsername(),
                    configuration.getApiInformation().getPassword(),
                    configuration.getApiInformation().getHmacKey()));
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }

        String payload = new Gson().toJson(request);
        HttpEntity requestEntity = new HttpEntity(payload, headers);

        ResponseEntity<GtdResponse> response;

        try {
            if (LOGGER.isDebugEnabled()) {
                String message = String.format("sending message: url='%s'", url);
                LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(coupaRequestContext.getCorrelationId()), escapeJava(message)));
            }
            startExternalRequestMonitoring(url);
            response = restTemplate.exchange(url + "/" + CALCULATE_DOC, HttpMethod.POST, requestEntity, GtdResponse.class);
            endExternalRequestMonitoring(url);
            if (LOGGER.isDebugEnabled()) {
                String message = String.format("received message: url='%s', response code='%d'", url, response.getStatusCodeValue());
                LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(coupaRequestContext.getCorrelationId()), escapeJava(message)));
            }
        } catch (ResourceAccessException e) {
            if (e.contains(SocketTimeoutException.class)) {
                throw new RecoverableException("SocketTimeoutException for url: " + configuration.getApiInformation().getUrl());
            }
            throw e;
        }
        return response;
    }

    public static String getAuthorizationString(String requestSuffix, String timeStamp, String username, String password, String hmacKey) throws GeneralSecurityException {
        return "TAX " + username + ":" + getHmacHeader(requestSuffix, timeStamp, username, password, hmacKey);
    }

    public static String getHmacHeader(String requestSuffix, String timeStamp, String username, String password, String hmacKey) throws GeneralSecurityException {
        String hmacFinalMessage = null;
        String securitySubject = username + password;
        String tweUrlPathBody = "/Twe/api/rest/" + requestSuffix;
        String hmacHeader = determinePlainStringHmacSignature(timeStamp, securitySubject, tweUrlPathBody);
        SecretKeySpec signingKey = new SecretKeySpec(hmacKey.getBytes(), "HMACSha1");
        Mac mac = Mac.getInstance("HMACSha1");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(hmacHeader.getBytes());
        hmacFinalMessage = DatatypeConverter.printBase64Binary(rawHmac);
        return hmacFinalMessage;
    }

    public static String determinePlainStringHmacSignature(String timeStamp, String securitySubject, String tweUrlPathBody) {
        return "POSTapplication/json" + timeStamp + tweUrlPathBody + securitySubject;
    }

    private void startExternalRequestMonitoring(String url) {
        if (url != null && url.contains("Twe")) {
            coupaRequestContext.startGtdTime();
        } else {
            coupaRequestContext.startAesTime();
        }
    }

    private void endExternalRequestMonitoring(String url) {
        if (url != null && url.contains("Twe")) {
            coupaRequestContext.endGtdTime();
        } else {
            coupaRequestContext.endAesTime();
        }
    }
}
