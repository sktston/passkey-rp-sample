package com.sktelecom.authentication.passkey.passkeyrpsample.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInitializer;

/**
 * Client http request initializer to set custom request headers for WebAuthn server
 */
@RequiredArgsConstructor
public class WebAuthnClientHttpRequestInitializer implements ClientHttpRequestInitializer {
    private static final String WEBAUTHN_RP_ID = "X-WebAuthentication-RpId";
    private final WebAuthnProperties webauthnProperties;

    @Override
    public void initialize(ClientHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        // set rp id
        headers.set(WEBAUTHN_RP_ID, webauthnProperties.getRp().getId());
    }
}
