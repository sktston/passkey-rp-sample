package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.common.ChallengeDto;
import com.sktelecom.authentication.fido2.server.dto.common.ServerResponseDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationResultDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationResultsServerRequestDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.configuration.WebAuthnProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.configuration.WebAuthnProperties.WebauthnServerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * {@inheritDoc}
 *
 * Implementation for RP REST Client
 */
@Component
public class WebAuthnRpRestClientImpl implements WebAuthnRpRestClient {
    // rest template for WebAuthn server
    private final RestTemplate restTemplate;
    private String registrationRequestUrl;
    private String registrationResponseUrl;
    private String authenticationRequestUrl;
    private String authenticationResponseUrl;

    public WebAuthnRpRestClientImpl(WebAuthnProperties webauthnProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        // initialize Webauthn server URLs
        initializeRequestUrls(webauthnProperties.getServer());
    }

    @Override
    public ServerResponseDto<ChallengeDto> getRegistrationOptions(RegistrationOptionsServerRequestDto request) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<ChallengeDto>> response =
            restTemplate.exchange(registrationRequestUrl, HttpMethod.POST, new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {
                });
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<RegistrationResultDto> postRegistrationResponse(RegistrationResultsServerRequestDto request) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<RegistrationResultDto>> response =
            restTemplate.exchange(registrationResponseUrl, HttpMethod.POST, new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {
                });
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<ChallengeDto> getAuthenticationOptions(AuthenticationOptionsServerRequestDto request) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<ChallengeDto>> response =
            restTemplate.exchange(authenticationRequestUrl, HttpMethod.POST, new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {
                });
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<AuthenticationResultDto> postAuthenticationResponse(AuthenticationResultsServerRequestDto request) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<AuthenticationResultDto>> response =
            restTemplate.exchange(authenticationResponseUrl, HttpMethod.POST, new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {
                });
        // @formatter:on
        return response.getBody();
    }

    private void initializeRequestUrls(WebauthnServerProperties serverProperties) {
        this.registrationRequestUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getRegistrationRequest();
        this.registrationResponseUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getRegistrationResponse();
        this.authenticationRequestUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getAuthenticationRequest();
        this.authenticationResponseUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getAuthenticationResponse();
    }
}
