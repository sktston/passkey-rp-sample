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

import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationResponseLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;

/**
 * Attestation (WebAuthn authentication) service interface
 */
public interface AttestationService {
    WebAuthnServerResponse<AttestationOptions> getOptions(AttestationOptionsServerRequest optionsRequest)
        throws WebAuthnServerException;

    WebAuthnServerResponse<AttestationOptions> getLv3Options(AttestationOptionsServerRequestLv3 optionsRequest)
        throws WebAuthnServerException;

    void handleResult(AuthenticatorResponseServerRequest<AttestationResponse> result, String requestId)
        throws WebAuthnServerException;

    void handleLv3Result(AuthenticatorResponseServerRequestLv3<AttestationResponseLv3> result, String requestId)
        throws WebAuthnServerException;
}
