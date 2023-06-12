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

package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.common.ChallengeDto;
import com.sktelecom.authentication.fido2.server.dto.common.CredentialStatusDto;
import com.sktelecom.authentication.fido2.server.dto.common.ServerResponseDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialIdDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialIdListResponseDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoListResponseDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoResponseDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialStatusUpdateDto;
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
    private String userUrl;
    private String userCredentialUrl;
    private String userCredentialsUrl;

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

    @Override
    public ServerResponseDto<CredentialIdListResponseDto> deleteUser(String userId) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<CredentialIdListResponseDto>> response =
            restTemplate.exchange(userUrl, HttpMethod.DELETE, HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }, userId);
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<CredentialInfoResponseDto> getUserCredential(String userId, String credentialId) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<CredentialInfoResponseDto>> response =
            restTemplate.exchange(userCredentialUrl, HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }, userId, credentialId);
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<CredentialInfoListResponseDto> getUserCredentials(String userId) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<CredentialInfoListResponseDto>> response =
            restTemplate.exchange(userCredentialsUrl, HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }, userId);
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<CredentialIdDto> updateCredentialStatus(String userId, String credentialId,
        CredentialStatusDto status) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<CredentialIdDto>> response =
            restTemplate.exchange(userCredentialUrl, HttpMethod.PATCH,
                new HttpEntity<>(new CredentialStatusUpdateDto(status)),
                new ParameterizedTypeReference<>() {
                }, userId, credentialId);
        // @formatter:on
        return response.getBody();
    }

    @Override
    public ServerResponseDto<CredentialIdDto> deleteCredential(String userId, String credentialId) {
        // @formatter:off
        ResponseEntity<ServerResponseDto<CredentialIdDto>> response =
            restTemplate.exchange(userCredentialUrl, HttpMethod.DELETE, HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }, userId, credentialId);
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
        this.userUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getUser();
        this.userCredentialUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getUserCredential();
        this.userCredentialsUrl = serverProperties.getBaseUrl()
            + serverProperties.getUrlPath().getUserCredentials();
    }
}
