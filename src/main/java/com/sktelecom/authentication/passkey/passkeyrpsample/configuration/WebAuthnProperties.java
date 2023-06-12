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

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

/**
 * WebAuthn server and related properties
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "webauthn")
@Value
@Validated
public class WebAuthnProperties {
    @NotNull
    @Valid
    RpProperties rp;
    @NotNull
    @Valid
    WebauthnServerProperties server;

    @Value
    public static class RpProperties {
        @NotEmpty
        String id;
    }

    @Value
    public static class WebauthnServerProperties {
        @NotEmpty
        String baseUrl;
        @NotEmpty
        String apiVersion;
        @NotNull
        @Valid
        UrlPaths urlPath;
        boolean oauth2Protected;
    }

    @Value
    public static class UrlPaths {
        @NotEmpty
        String registrationRequest;
        @NotEmpty
        String registrationResponse;
        @NotEmpty
        String authenticationRequest;
        @NotEmpty
        String authenticationResponse;
        @NotEmpty
        String user;
        @NotEmpty
        String userCredential;
        @NotEmpty
        String userCredentials;
    }
}
