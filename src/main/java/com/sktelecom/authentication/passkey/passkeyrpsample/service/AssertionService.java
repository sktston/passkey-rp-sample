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
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AssertionOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;

/**
 * Assertion (WebAuthn authentication) service interface
 */
public interface AssertionService {
    WebAuthnServerResponse<AssertionOptions> getOptions(AssertionOptionsServerRequest optionsRequest)
        throws WebAuthnServerException;

    void handleResult(AuthenticatorResponseServerRequest<AssertionResponse> result, String requestId)
        throws WebAuthnServerException;

    WebAuthnServerResponse<AssertionOptions> getLv3Options(AssertionOptionsServerRequestLv3 optionsRequest)
        throws WebAuthnServerException;

    void handleLv3Result(AuthenticatorResponseServerRequestLv3<AssertionResponse> result, String requestId)
        throws WebAuthnServerException;
}
