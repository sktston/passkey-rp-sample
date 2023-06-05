package com.sktelecom.authentication.passkey.passkeyrpsample.exception;

import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.ServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    public final ResponseEntity<Object> handleException(RestClientException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        // if the WebAuthn server responses an error, consider it as internal server error
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        ServerResponse serverResponse = new ServerResponse(Status.FAILED, ex.getMessage());
        return super.handleExceptionInternal(ex, serverResponse, headers, status, request);
    }
}
