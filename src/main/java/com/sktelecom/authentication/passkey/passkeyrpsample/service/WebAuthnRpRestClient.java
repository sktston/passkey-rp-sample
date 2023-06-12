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
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationResultDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationResultsServerRequestDto;

/**
 * Define interfaces for RP (Relying Party) REST client to interact with the WebAuthn server
 */
public interface WebAuthnRpRestClient {
    /**
     * Get registration options from the WebAuthn server
     * @param request request dto for POST /v1/registration/request
     * @return server response from the WebAuthn server containing registration options
     */
    ServerResponseDto<ChallengeDto> getRegistrationOptions(RegistrationOptionsServerRequestDto request);

    /**
     * Send registration response (result) to the WebAuthn server to finish registration
     * @param request request dto for POST /v1/registration/response
     * @return server response from the WebAuthn server containing registration result
     */
    ServerResponseDto<RegistrationResultDto> postRegistrationResponse(RegistrationResultsServerRequestDto request);

    /**
     * Get authentication options from the WebAuthn server
     * @param request request dto for POST /v1/authentication/request
     * @return server response from the WebAuthn server containing authentication options
     */
    ServerResponseDto<ChallengeDto> getAuthenticationOptions(AuthenticationOptionsServerRequestDto request);

    /**
     * Send authentication response (result) to the WebAuthn server to finish authentication
     * @param request request dto for POST /v1/authentication/response
     * @return server response from the WebAuthn server containing authentication result
     */
    ServerResponseDto<AuthenticationResultDto> postAuthenticationResponse(
        AuthenticationResultsServerRequestDto request);

    /**
     * Delete given user and associated user's credential
     * @param userId user id intended to delete from the WebAuthn server
     * @return server response from WebAuthn server containing list of deleted credential ids for the given user id
     */
    ServerResponseDto<CredentialIdListResponseDto> deleteUser(String userId);

    /**
     * Get credential information associated to user id and credential id
     * @param userId given user id
     * @param credentialId given credential id
     * @return server response from the WebAuthn server containing the credential information
     */
    ServerResponseDto<CredentialInfoResponseDto> getUserCredential(String userId, String credentialId);

    /**
     * Get list of credential information associated to the user id
     * @param userId given user id
     * @return server response from the WebAuthn server containing list of the credential information
     */
    ServerResponseDto<CredentialInfoListResponseDto> getUserCredentials(String userId);

    /**
     * Update credential status
     * @param userId given user id
     * @param credentialId given credential id
     * @param status to be credential status
     * @return server response form the WebAuthn server containing credential id which state was successfully updated
     */
    ServerResponseDto<CredentialIdDto> updateCredentialStatus(String userId, String credentialId, CredentialStatusDto status);

    /**
     * Delete a credential
     * @param userId user id of the credential owner
     * @param credentialId credential id to delete
     * @return server response from the WebAuthn server containing credential id which is deleted successfully
     */
    ServerResponseDto<CredentialIdDto> deleteCredential(String userId, String credentialId);
}
