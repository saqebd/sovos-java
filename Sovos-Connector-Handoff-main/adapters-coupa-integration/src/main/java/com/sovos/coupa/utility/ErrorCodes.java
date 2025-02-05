/**
 * Copyright (c) 2021 by Sovos Compliance
 */
package com.sovos.coupa.utility;

public enum ErrorCodes {
    // General Error - 0000
    GENERAL_ERROR("0000", "General Error"),
    FORBIDDEN("0001", "Forbidden"),
    FORBIDDEN_MISSING_PERMISSIONS("0002", "Missing permissions"),
    // General Validation Errors -0010
    GENERAL_VALIDATION_NULL_EMPTY("0011", "Assert Null/Empty Failure, field: '%s' value: '%s'"),
    GENERAL_VALIDATION_LIST_NULL_EMPTY("0011", "Assert Null/Empty Failure, field: '%s'"),
    GENERAL_VALIDATION_NULL("0012", "Assert Null Failure, field: '%s'"),
    GENERAL_VALIDATION_FORMAT_FAILURE("0013", "Assert format Failure, field: '%s', value: '%s', expected format: '%s'"),
    GENERAL_VALIDATION_MAX_LENGTH_FAILURE("0014", "Assert length Failure, field: '%s', value: '%s', invalid-length: '%d', expected max-length: '%d'"),
    GENERAL_VALIDATION_MIN_LENGTH_FAILURE("0015", "Assert length Failure, field: '%s', value: '%s', invalid-length: '%d', expected min-length: '%d'"),
    GENERAL_VALIDATION_DATE_FAILURE("0016", "Assert format Failure, field: '%s', value: '%s', expected format: '%s'"),
    // Administration violations - 0100
    INVALID_USER_ID("0101", "Missing or invalid S1 Account ID"),
    ACCOUNT_PROPERTY_CARDINALITY("0102", "This account has more than on property"),
    ACCOUNT_PROPERTY_CARDINALITY_FORMAT("0102", "Account: '%s' has more than on property"),
    INTERNAL_INVALID_USER_INFORMATION("0103", "User information cannot be null"),
    // Connectivity Information - 0200
    GTD_CREDENTIALS("0201", "GTD Credentials problem.  Review GTD account linkage, and then retry."),
    GTD_TIMEOUT_DELETE("0202", "A request made to GTD timed out and exceeded the retry limit. Transaction might have been deleted."),
    GTD_TIMEOUT("0202", "A request made to GTD timed out and exceeded the retry limit."),
    GTD_CONNECTION_FAULT("0204", "GTD Connection Fault."),
    // Transaction value violations - 1000
    INVOICE_HAS_TOO_MANY_LINES("1001", "Invoice has more than "),
    INVALID_POSTAL_CODE("1002", "Postal Code should be formatted 09999-0999"),
    INVALID_TRANSACTION_REQUEST("1003", "Missing or empty transaction requests"),
    INVALID_DISCOUNT_AMOUNT("1004", "Missing or Invalid discount amount"),
    INVALID_INVOICE_NUMBER_LENGTH("1005", "Input Field: Invoice Number With Length Exceeded The Max: 40"),
    INVALID_REQUEST_PARAMETERS("1006", "Invalid Request Parameters"),
    INVALID_INVOICE_NUMBER("1007", "Missing or Invalid Invoice Number"),
    INVALID_SALE_DATE("1008", "Missing or Invalid invoice Date"),
    INVALID_INVOICE_DATE_FORMAT("1008", "Missing or Invalid invoice date format: '%s' expected format: '%s'"),
    INVALID_CURRENCY_CODE("1009", "Invalid Currency Code"),
    MISSING_LINE_ITEMS("1010", "Missing Line Items"),
    INVALID_GROSS_AMOUNT("1011", "Missing or Invalid Gross Amount"),
    INVALID_COMMIT_DATE("1012", "Missing or Invalid Commit Date"),
    INVALID_COMMIT_DATE_FORMAT("1013", "Missing or Invalid commit date: '%s' expected format: '%s'"),
    INVALID_DATE_RANGE("1014", "Missing or Invalid Date Range"),
    INVALID_CREDIT_DEBIT_INDICATOR("1015", "Invalid Credit / Debit Indicator"),
    // Transaction state violations - 2000
    PERIOD_LOCK_ALREADY_EXISTS("2001", "Dates correspond to a previously locked period"),
    PERIOD_LOCK_ALREADY_EXISTS_FORMAT("2001", "'%s' : '%s', correspond to a previously locked period"),
    INVALID_PRIOR_TRANSACTION_ID_FORMAT("2002", "Missing or Invalid PriorTransactionId: '%s'"),
    INVALID_PRIOR_TRANSACTION_ID("2002", "Missing or Invalid PriorTransactionId"),
    INVALID_INVOICE_REQUEST_OR_PRIOR_TRANSACTION_ID("2003", "Missing InvoiceRequest or PriorTransactionId"),
    INVALID_COMMIT_DATE_COMPARED_TO_INVOICE_DATE("2004", "Invalid Commit Date: '%s' compared to invoice date: '%s'"),
    INVALID_REQUESTED_TRANSACTION_STATE("2005", "Invalid requested transactional state"),
    // Configurable violations - 3000
    CONFIGURABLE_VIALATION_EXCEEDS_LINE_LENGTH("3000", "Transaction contains '%d' line(s), exceeds the allowable max '%d'. Please contact account represenative.");


    private String code;
    private String message;

    ErrorCodes(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {

        return code;
    }

    public String getMessage() {

        return message;
    }

}
