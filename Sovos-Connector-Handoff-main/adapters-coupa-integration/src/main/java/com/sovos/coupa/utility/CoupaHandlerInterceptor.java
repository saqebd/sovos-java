package com.sovos.coupa.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: CHECK IF WE NEED THIS ALREADY REGISTERED IN WebConfig.class
@Component
public class CoupaHandlerInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LogManager.getLogger("monitoring");
    public static final String X_CORRELATION_ID = "x-correlation-id";
    public static final String X_REQUEST_ID = "x-request-id";
    public static final String X_IMPLEMENTER_ID = "x-implementer-id";

    @Autowired
    CoupaRequestContext coupaRequestContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        coupaRequestContext.setCorrelationId(request.getHeader(X_CORRELATION_ID));
        coupaRequestContext.setRequestId(request.getHeader(X_REQUEST_ID));
        coupaRequestContext.setImplementerId(request.getHeader(X_IMPLEMENTER_ID));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (LOGGER.isInfoEnabled()) {
            if (ex != null) {
                coupaRequestContext.setMessage(ex.getMessage());
            }
            coupaRequestContext.setCode(response.getStatus());
        }
    }
}
