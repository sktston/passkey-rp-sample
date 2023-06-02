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
public class WebauthnProperties {
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
    }
}
