package com.sovos.coupa.utility;

import com.sovos.platform.PlatformException;

public class RecoverableException extends PlatformException {

    private static final long serialVersionUID = -1405743287157848647L;

    /**
     * Default Constructor for RecoverableException exception class
     */
    public RecoverableException() {

        super();
    }

    /**
     * Constructor for Platform Services exception class
     *
     * @param message Exception message
     */
    public RecoverableException(String message) {

        super(message);
    }

    /**
     * Constructor for RecoverableException exception class
     *
     * @param message Exception message
     * @param cause   Exception cause
     */
    public RecoverableException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Constructor for Platform Services exception class
     *
     * @param cause Exception clause
     */
    public RecoverableException(Throwable cause) {

        super(cause);
    }

    /**
     * Constructor for Platform Services exception class
     *
     * @param message Exception message
     * @param code    the code of the exception
     */
    public RecoverableException(String message, String code) {

        super(message);
        this.setErrorCode(code);
    }

    /**
     * Constructor for Platform Services exception class
     *
     * @param message Exception message
     * @param cause   Exception cause
     * @param code    the code of the exception
     */
    public RecoverableException(String message, Throwable cause, String code) {

        super(message, cause);
        this.setErrorCode(code);
    }

}
