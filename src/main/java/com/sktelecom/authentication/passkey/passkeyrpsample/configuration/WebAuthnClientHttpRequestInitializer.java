/*
 * Copyright (C) 2023 SK TELECOM CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
