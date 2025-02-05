package com.sovos.coupa.service;

import java.lang.invoke.MethodHandles;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sovos.coupa.config.GtdConfig;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.CoupaRequestContext;
import com.sovos.coupa.utility.ErrorCodes;
import com.sovos.coupa.config.RecoverableException;
import com.sovos.coupa.domain.ApiInformation;
import com.sovos.integrations.gtd.HmacUtility;
import com.sovos.platform.ServiceInternalException;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;

@Service
public class RestService {
    public static final String HEADERS_DATE = "Date";
    public static final String HTTPS = "https";
    private static final String GTD_VERSION = "/version";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    GtdConfig gtdConfig;

    @Autowired
    private CoupaRequestContext coupaRequestContext;

    public <T> ResponseEntity<T> getResponse(RestTemplate restTemplate, String url,
                                             HttpMethod method, String request, Map<String, String> headers, Class<T> clazz,
                                             MediaType contentType) {

        ResponseEntity<T> response;
        HttpEntity<String> entity = buildEntity(request, headers, contentType);
        try {
            response = restTemplate.exchange(url, method, entity, clazz);
        } catch (ResourceAccessException e) {
            LOGGER.error(Encode.forJava(String.format("error: %s", e.getMessage())));
            if (e.contains(SocketTimeoutException.class)) {
                throw new RecoverableException("SocketTimeoutException for url: " + url + ", will attempt retry.");
            }
            throw e;
        }
        return response;
    }

    public int getGtdHealthStatus() {

        ResponseEntity<String> response;

        RestTemplateBuilder templateBuilder =
                extractBaseGtdRestTemplateBuilder(gtdConfig.getGtdTimeoutBaseInMilliseconds());
        RestTemplate restTemplate = templateBuilder.build();
        if (LOGGER.isDebugEnabled()) {
            String message = "Sending Health check request to GTD";
            LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, null, message));
        }
        try {
            response = restTemplate
                    .exchange(gtdConfig.getGtdUrl() + GTD_VERSION,
                            HttpMethod.GET, null, String.class);
        } catch (Exception e) {
            LOGGER.error(Encode.forJava(String.format("error: %s", e.getMessage())));
            throw new RecoverableException(
                    "SocketTimeoutException for url: " + gtdConfig.getGtdUrl() + ", will attempt retry.");
        }
        if (LOGGER.isDebugEnabled()) {
            String message = String.format("Received GTD response with code: '%d'",
                    response.getStatusCodeValue());
            LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, null, message));
        }

        return response.getStatusCodeValue();
    }

    private HttpEntity<String> buildEntity(String request, Map<String, String> headers, MediaType contentType) {
        HttpHeaders entityHeaders = new HttpHeaders();
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                entityHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        entityHeaders.setContentType(contentType);
        return new HttpEntity<>(request, entityHeaders);
    }

    public Map<String, String> buildGtdHeaders(ApiInformation apiInformation, String url) {

        Map<String, String> headers = new HashMap<>();
        String timestamp = DatatypeConverter.printDateTime(Calendar.getInstance());
        headers.put(HEADERS_DATE, timestamp);
        if (apiInformation.getUrl().startsWith(HTTPS)) {
            try {
                String auth = HmacUtility.getAuthorizationString(url, timestamp,
                        apiInformation.getUsername(), apiInformation.getPassword(),
                        apiInformation.getHmacKey());
                headers.put("Authorization", auth);
            } catch (GeneralSecurityException e) {
                throw new ServiceInternalException(
                        "Exception generating GTD HMAC signature: " + e.getMessage(), e,
                        ErrorCodes.GENERAL_ERROR.getCode());
            }

        }
        return headers;
    }

    public RestTemplateBuilder extractBaseGtdRestTemplateBuilder(Integer timeoutInMilliseconds) {
        RestTemplateBuilder templateBuilder = new RestTemplateBuilder().errorHandler(new GtdRestErrorHandler()).setReadTimeout(Duration.ofMillis(timeoutInMilliseconds));
        return templateBuilder;
    }
}

