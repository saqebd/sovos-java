package com.sovos.coupa.utility;

import com.sovos.platform.PlatformException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class HttpUnauthorizedException
        extends PlatformException {

    public HttpUnauthorizedException(String message, String code) {

        super(message, code);
    }
}