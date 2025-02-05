package com.sovos.coupa.servlet;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.CoupaRequestContext;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;


@Component
public class PreferredResponseMediaTypeFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String PARAM_QUALITY_FACTOR = "q";

    @Autowired
    private CoupaRequestContext requestContext;

    public PreferredResponseMediaTypeFilter() {

        super();

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        MediaType givenMediaType;
        List<MediaType> acceptableMediaTypes;

        try {

            givenMediaType = getGivenContentType(httpReq);
            acceptableMediaTypes = getAcceptableMediaTypes(httpReq);

        } catch (Exception e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(requestContext.getCorrelationId()), "MediaType parsing exception, continue without wrapping the request servlet"));
            }
            filterChain.doFilter(httpReq, httpRes);
            return;

        }

        HttpServletRequestPreferredAccept httpReqWrapper =
                new HttpServletRequestPreferredAccept((HttpServletRequest) req);

        addAcceptHeadersWithPreferredMediaType(acceptableMediaTypes, givenMediaType, httpReqWrapper);

        filterChain.doFilter(httpReqWrapper, httpRes);

    }

    @Override
    public void destroy() {

    }

    private void addAcceptHeadersWithPreferredMediaType(List<MediaType> acceptableMediaTypes,
                                                        MediaType givenMediaType, HttpServletRequestPreferredAccept httpReqWrapper) {

        if (acceptableMediaTypes.isEmpty()) {

            // preferred media type only
            httpReqWrapper.addAcceptHeader(givenMediaType.toString());

        } else {

            // add concrete types first
            List<MediaType> concreteMediaTypes = getConcreteMediaTypes(acceptableMediaTypes);
            concreteMediaTypes.stream().map(MimeType::toString)
                    .forEach(httpReqWrapper::addAcceptHeader);

            // continue with wildcards
            List<MediaType> wildCardMediaTypes = getWildcardMediaTypes(acceptableMediaTypes);
            boolean isTypeAndSubtypeAlreadyIncluded =
                    isTypeAndSubtypeAlreadyIncluded(givenMediaType, acceptableMediaTypes);

            if (isTypeAndSubtypeAlreadyIncluded) {

                wildCardMediaTypes.stream().map(MimeType::toString)
                        .forEach(httpReqWrapper::addAcceptHeader);

            } else {

                List<MediaType> modifiedMediaTypeList =
                        addPreferredMediaType(givenMediaType, wildCardMediaTypes);
                modifiedMediaTypeList.stream().map(MimeType::toString)
                        .forEach(httpReqWrapper::addAcceptHeader);

            }

        }

    }

    private List<MediaType> addPreferredMediaType(MediaType givenMediaType, List<MediaType> wildCardMediaTypes) {

        List<MediaType> list = new ArrayList<>();

        for (MediaType acceptableType : wildCardMediaTypes) {

            if (givenMediaType.isCompatibleWith(acceptableType)) {

                MediaType preferredMediaType = null;

                if (acceptableType.getParameter(PARAM_QUALITY_FACTOR) != null) {
                    preferredMediaType = new MediaType(givenMediaType.getType(),
                            givenMediaType.getSubtype(),
                            acceptableType.getQualityValue());
                } else {
                    preferredMediaType = new MediaType(givenMediaType.getType(),
                            givenMediaType.getSubtype());
                }

                list.add(preferredMediaType);

            }

            list.add(acceptableType);

        }

        return list;

    }

    private List<MediaType> getWildcardMediaTypes(List<MediaType> acceptableMediaTypes) {

        List<MediaType> list = new ArrayList<>();

        acceptableMediaTypes.stream()
                .filter(x -> !x.isConcrete())
                .forEach(list::add);

        return list;

    }

    private List<MediaType> getConcreteMediaTypes(List<MediaType> acceptableMediaTypes) {

        List<MediaType> list = new ArrayList<>();

        acceptableMediaTypes.stream()
                .filter(MimeType::isConcrete)
                .forEach(list::add);

        return list;

    }

    private boolean isTypeAndSubtypeAlreadyIncluded(MediaType givenMediaType, List<MediaType> acceptableMediaTypes) {

        for (MediaType acceptableType : acceptableMediaTypes) {
            if (givenMediaType.getType().equals(acceptableType.getType()) &&
                    givenMediaType.getSubtype().equals(acceptableType.getSubtype())) {

                return true;

            }
        }

        return false;

    }

    private MediaType getGivenContentType(HttpServletRequest request)
            throws HttpMediaTypeNotSupportedException {

        MediaType contentType = null;

        if (StringUtils.hasLength(request.getContentType())) {
            try {
                contentType = MediaType.parseMediaType(request.getContentType());

                return contentType;
            } catch (InvalidMediaTypeException ex) {
                throw new HttpMediaTypeNotSupportedException(ex.getMessage());
            }
        }

        throw new HttpMediaTypeNotSupportedException("Empty Content-Type");

    }

    private List<MediaType> getAcceptableMediaTypes(HttpServletRequest request)
            throws HttpMediaTypeNotAcceptableException {

        String[] headerValueArray = StringUtils.toStringArray(
                request.getHeaders(HttpHeaders.ACCEPT));

        List<String> headerValues = Arrays.asList(headerValueArray);

        try {
            List<MediaType> producibleMediaTypes = MediaType.parseMediaTypes(headerValues);
            MediaType.sortBySpecificityAndQuality(producibleMediaTypes);

            return producibleMediaTypes;
        } catch (InvalidMediaTypeException ex) {
            throw new HttpMediaTypeNotAcceptableException(
                    "Could not parse 'Accept' header " + headerValues + ": " + ex.getMessage());
        }

    }

}
