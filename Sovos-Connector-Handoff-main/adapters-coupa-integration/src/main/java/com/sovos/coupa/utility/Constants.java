package com.sovos.coupa.utility;

public class Constants {

    private Constants() {

        throw new IllegalStateException("Do not instantiate this static class");
    }

    public static final String EMPTY_STRING = "";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_GET = "GET";

    public static final String HTTP_RQ_PROPERTY_NAME = "Content-Type";
    public static final String HTTP_RQ_TYPE_JSON = "application/json";

    public static final String ORIGINAL = "original";
    public static final String ENHANCED = "enhanced";

    public static final String SQUIRE = "Squire";
    public static final String SIMPLE_AR = "Simple AR";

    public static final String REMOVE_ACC_CACHE_MESSAGE = "REMOVE_ACCOUNT_CACHE";

    public static final int MAXIMUM_INVOICE_NUMBER_LENGTH = 40;

    /* Algorithm used to generate digital signature keys. */
    public static final String HMAC_SHA256 = "HmacSHA256";

    /*
     * Regular expression which when matches, identifies an illegal character
     * for fields involved in a digital signature.
     */
    public static final String ILLEGAL_SIGNATURE_REGEX = "[^\r]+$";

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String APPLICATION_CODE = "drtx";
    public static final String PENDING_STATUS = "PENDING";

    public static final String REQUEST_ID_KEY = "X-request-id";
    public static final String CORRELATION_ID_KEY = "X-correlation-id";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REQUESTDATE = "x-request-date";

    public static final String GENERAL_LOG_FORMAT = "{\"correlationId\":\"%s\",\"message\":\"%s\"}";
    public static final String PAYLOAD_LOG_FORMAT = "{\"correlationId\":\"%s\",\"message\":\"%s\", \"payload\":%s}";

    public static final String AMOUNT_LINE = "InvoiceAmountLine";

    public static final String REQUEST_LOG_FILE_NAME = "request.log";
}
