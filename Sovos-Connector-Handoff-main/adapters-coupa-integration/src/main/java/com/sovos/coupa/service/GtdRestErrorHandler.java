package com.sovos.coupa.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.google.gson.Gson;
import com.sovos.coupa.utility.ErrorCodes;
import com.sovos.coupa.utility.RecoverableException;
import com.sovos.integrations.gtd.SovosResponse;
import com.sovos.platform.ServiceExternalException;
import com.sovos.platform.ServiceInternalException;

@Component
public class GtdRestErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        int statusCode = response.getStatusCode().value();
        return (statusCode >= 400 && statusCode < 600);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        StringBuilder textBuilder = readBody(response.getBody());
        if (textBuilder.toString().contains("errorCode")) {
            SovosResponse error = new Gson().fromJson(textBuilder.toString(), SovosResponse.class);
            throw new ServiceExternalException(error.getErrorMessage(), error.getErrorCode());
        }
        if (textBuilder.toString().contains("Unable to Retrieve Key")) {
            throw new ServiceInternalException("Invalid GTD Authentication Key",
                    ErrorCodes.GTD_CREDENTIALS.getCode());
        }
        if (textBuilder.toString().contains("Service Not Found.")) {
            throw new ServiceInternalException("GTD Service Not Found",
                    ErrorCodes.GTD_CONNECTION_FAULT.getCode());
        }
        throw new RecoverableException("Recoverable error detected during rest request");
    }

    private StringBuilder readBody(InputStream body) throws IOException {

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(
                new InputStreamReader(body, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder;
    }
}
