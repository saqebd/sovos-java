package com.sovos.coupa.service;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.google.gson.Gson;
import com.sovos.coupa.config.GtdRestErrorHandler;
import com.sovos.coupa.config.IAMConfig;
import com.sovos.coupa.domain.IamOrgResponse;
import com.sovos.coupa.domain.IamResponse;
import com.sovos.coupa.utility.CoupaRequestContext;
import com.sovos.coupa.utility.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
public class S1RestService extends RestService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String IAM_ORGS = "/orgs/";
    private static final String CONFIG_TRUE = "?config=true";
    public static final String HEADERS_DATE = "x-request-date";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN = "token";

    @Autowired
    IAMConfig IAMConfig;

    @Autowired
    CoupaRequestContext requestContext;

    public IamOrgResponse getCoupaAccount(String s1AccountId) {

        IamOrgResponse iamOrgResponse = null;
        Map<String, String> headers = buildS1Headers();
        RestTemplateBuilder templateBuilder = extractBaseIamRestTemplateBuilder(IAMConfig.getIamTimeoutBaseInMilliseconds());
        RestTemplate restTemplate = templateBuilder.build();
        if (LOGGER.isDebugEnabled()) {
            String message = "Sending GET_ORGS request to IAM service";
            LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(requestContext.getCorrelationId()), escapeJava(message)));
        }
        ResponseEntity<String> responseFromIAM = this.getResponse(restTemplate,
                IAMConfig.getIamServiceUrl() + IAM_ORGS + s1AccountId + CONFIG_TRUE, HttpMethod.GET, null,
                headers, String.class, MediaType.APPLICATION_JSON);
        if (LOGGER.isDebugEnabled()) {
            String message = String.format("Received IAM response with code: '%d'", responseFromIAM.getStatusCodeValue());
            LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, requestContext.getCorrelationId(), message));
        }
        IamResponse iamResponse = new Gson().fromJson(responseFromIAM.getBody(), IamResponse.class);
        iamOrgResponse = iamResponse.getData().get(0);

        return iamOrgResponse;
    }

    private Map<String, String> buildS1Headers() {

        Map<String, String> headers = new HashMap<>();
        String timestamp = DatatypeConverter.printDateTime(Calendar.getInstance());
        headers.put(HEADERS_DATE, timestamp);
        headers.put(AUTHORIZATION, requestContext.getMetaData().get(TOKEN));

        return headers;
    }

    private RestTemplateBuilder extractBaseIamRestTemplateBuilder(Integer timeoutInMilliseconds) {

        RestTemplateBuilder templateBuilder =
                new RestTemplateBuilder().errorHandler(new GtdRestErrorHandler())
                        .setReadTimeout(Duration.ofMillis(timeoutInMilliseconds));
        return templateBuilder;
    }

}

