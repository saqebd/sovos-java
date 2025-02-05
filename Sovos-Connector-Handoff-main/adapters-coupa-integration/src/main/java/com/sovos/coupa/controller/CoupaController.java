package com.sovos.coupa.controller;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.google.gson.Gson;
import com.sovos.coupa.config.ConfigurationService;
import com.sovos.coupa.config.GtdConfig;
import com.sovos.coupa.controller.dto.CoupaRequest;
import com.sovos.coupa.controller.dto.CoupaResponse;
import com.sovos.coupa.controller.dto.GtdRequest;
import com.sovos.coupa.controller.dto.GtdResponse;
import com.sovos.coupa.service.CoupaTransactionService;
import com.sovos.coupa.service.CoupaTranslatorService;
import com.sovos.coupa.service.RestService;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.CoupaRequestContext;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoupaController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String NAMESPACE = "CPA";

    @Autowired
    private CoupaRequestContext requestContext;
    @Autowired
    private CoupaTranslatorService coupaTranslator;
    @Autowired
    private RestService restService;
    @Autowired
    private CoupaTransactionService coupaTransaction;
    @Autowired
    GtdConfig gtdConfig;
    @Autowired
    private ConfigurationService configuration;
    @Value("${logging.path}")
    private String logPath;


    @PostMapping(value = "/api/coupa/v1/transactions/calculate", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE})
    @PreAuthorize("hasAnyAuthority('COUPA_C_TRNS')")
    public ResponseEntity<CoupaResponse> createTransaction(@RequestBody CoupaRequest request) throws Exception {

        ResponseEntity<CoupaResponse> response = null;
        if (LOGGER.isInfoEnabled()) {
            String message = "Received Invoice calculation request";
            LOGGER.info(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(requestContext.getCorrelationId()), escapeJava(message)));
        }

        if (request.getCustomFields() == null || !request.getCustomFields().getBypassGTD()) {
            GtdRequest gtdRequest = coupaTranslator.translateRequest(request);
            writeToUserFile(gtdRequest, "To GTD: " + new Gson().toJson(gtdRequest), gtdRequest.getTrnSrc());
            ResponseEntity<GtdResponse> gtdResponse;
            try {
                gtdResponse = coupaTransaction.executeEvaluate(gtdRequest, gtdConfig.getGtdUrl());
            }catch(Exception e){
                throw new Exception(e.getMessage().replace("<EOL>",""));
            }
            writeToUserFile(gtdRequest, "From GTD: " + new Gson().toJson(gtdResponse.getBody()), gtdRequest.getTrnSrc());
            CoupaResponse coupaResponse = coupaTranslator.translateResponse(gtdResponse);
            writeToUserFile(gtdRequest, "To ERP: " + new Gson().toJson(coupaResponse), gtdRequest.getTrnSrc());
            response = new ResponseEntity<>(coupaResponse, HttpStatus.OK);
        }

        return response;
    }

    private void writeToUserFile(GtdRequest gtdRequest, String message, String transactionSource) throws IOException {

        Path basePath = Paths.get(logPath).toRealPath();

        if (basePath.toFile().exists()) {
            Path targetPath = basePath.resolve(sanitizeFileName(transactionSource)).normalize();

            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
                Path resolveFile = targetPath.resolve(Constants.REQUEST_LOG_FILE_NAME);
                Files.writeString(resolveFile, "");
            }

            targetPath = targetPath.toRealPath();

            if (!targetPath.startsWith(Paths.get(logPath))) {
                throw new SecurityException("Invalid path");
            }

            if (message.contains("pswrd")) {
                int i = message.indexOf("pswrd");
                int j = message.indexOf(",", i);
                message = message.replace(message.substring(i, j), "pswrd\":\"*****\"");
            }

            Path requestLogFilePath = targetPath.resolve(Constants.REQUEST_LOG_FILE_NAME);
            Files.writeString(requestLogFilePath, "\n" + DateTime.now() + ": correlationId:" + requestContext.getCorrelationId() + ": " + message, StandardOpenOption.APPEND);
        }
    }

    public static String sanitizeFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name cannot be null or empty.");
        }

        String normalized = Normalizer.normalize(fileName, Normalizer.Form.NFKC);

        return Pattern.compile("[^a-zA-Z0-9-_\\.]").matcher(normalized).replaceAll("_");
    }

}
