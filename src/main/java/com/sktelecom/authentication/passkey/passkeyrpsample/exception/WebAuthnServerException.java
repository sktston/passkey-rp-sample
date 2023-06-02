package com.sktelecom.authentication.passkey.passkeyrpsample.exception;

public class WebAuthnServerException extends RuntimeException {
    private static final long serialVersionUID = -784331634328110133L;

    public WebAuthnServerException(String message) {
        super(message);
    }

    public WebAuthnServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
