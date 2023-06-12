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

import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import java.util.List;

/**
 * Credentials associated to the user related service interface
 */
public interface UserCredentialService {
    /**
     * Get a credential for the given user id with credential id
     * @param userId given user's identifier
     * @param credentialId credential identifier looking for
     * @return credential information
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    CredentialInfoDto getUserCredential(String userId, String credentialId) throws WebAuthnServerException;

    /**
     * Get list of credential's information for the given user
     * @param userId given user's identifier
     * @return list of credential's information
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    List<CredentialInfoDto> getUserCredentials(String userId) throws WebAuthnServerException;

    /**
     * Activate and enable user's credential to accept the authentication with the credential
     * @param userId user's identifier
     * @param credentialId credential identifier
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    void activateUserCredential(String userId, String credentialId) throws WebAuthnServerException;

    /**
     * Deactivate and disable user's credential
     * @param userId user's identifier
     * @param credentialId credential identifier
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    void deactivateUserCredential(String userId, String credentialId) throws WebAuthnServerException;

    /**
     * Remove user's credential
     * @param userId user's identifier
     * @param credentialId identifier of the credential to be deleted
     * @return identifier of the credential successfully removed
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    String removeUserCredential(String userId, String credentialId) throws WebAuthnServerException;
}
