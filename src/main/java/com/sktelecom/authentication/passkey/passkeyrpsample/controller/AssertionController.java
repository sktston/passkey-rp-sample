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

package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication.AssertionOptionsServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.ServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AssertionOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.AssertionService;
import com.sktelecom.authentication.passkey.passkeyrpsample.util.CookieUtil;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for authentication process
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class AssertionController {
    private static final String COOKIE_NAME = "assertion_request_id";
    private final AssertionService assertionService;

    /**
     * For conformance testing purpose
     */
    @PostMapping(path = "/assertion/options")
    AssertionOptionsServerResponse authenticationChallenge(@Valid @RequestBody AssertionOptionsServerRequest request,
        HttpServletResponse httpServletResponse) {
        WebAuthnServerResponse<AssertionOptions> optionsResponse = assertionService.getOptions(request);
        AssertionOptions options = optionsResponse.getOptions();
        // set cookie to track request and subsequent response
        CookieUtil.addCookie(httpServletResponse, COOKIE_NAME, optionsResponse.getSessionId());
        return convert(options);
    }

    /**
     * For conformance testing purpose
     */
    @PostMapping(path = "/assertion/result")
    ServerResponse authenticationResponse(@CookieValue(name = COOKIE_NAME) String requestId,
        @Valid @RequestBody AuthenticatorResponseServerRequest<AssertionResponse> result) {
        assertionService.handleResult(result, requestId);
        return new ServerResponse(Status.OK, "");
    }

    /**
     * Handler to start WebAuthn authentication process
     * @param request parameters for authentication options, you may use your own model,
     *                most of the cases, such values are populated in the backend.
     *                If the server somehow identifies the user (user id) first
     *                (e.g., from the username input field or 2nd factor authentication use cases),
     *                The server needs to set identified user id within the request
     *                so that the WebAuthn server only populated candidate credentials for the given user id.
     *                This sample application does generate user id dynamically with given username.
     *                Check this {@link AssertionOptionsServerRequestMapper#toWebauthnUserId(String username) model mapper }.
     * @param httpServletResponse servlet response
     * @return authentication options
     */
    @PostMapping(path = "/lv3/assertion/options")
    AssertionOptionsServerResponse authenticationChallengeLv3(
        @Valid @RequestBody AssertionOptionsServerRequestLv3 request,
        HttpServletResponse httpServletResponse) {
        WebAuthnServerResponse<AssertionOptions> optionsResponse = assertionService.getLv3Options(request);
        AssertionOptions options = optionsResponse.getOptions();

        // You might have different way to manage the WebAuthn authentication session (transaction)
        // set cookie to track request and subsequent response for simplicity
        CookieUtil.addCookie(httpServletResponse, COOKIE_NAME, optionsResponse.getSessionId());
        return convert(options);
    }

    /**
     * Handler for handling authentication response from the client
     * @param requestId session id indicating the corresponding authentication option
     * @param result authentication response from the client
     * @return authentication result, you may use your own model
     */
    @PostMapping(path = "/lv3/assertion/result")
    ServerResponse authenticationResponseLv3(@CookieValue(name = COOKIE_NAME) String requestId,
        @Valid @RequestBody AuthenticatorResponseServerRequestLv3<AssertionResponse> result) {
        assertionService.handleLv3Result(result, requestId);
        return new ServerResponse(Status.OK, "");
    }

    private AssertionOptionsServerResponse convert(AssertionOptions options) {
        // @formatter:off
        return AssertionOptionsServerResponse.builder()
            .challenge(options.getChallenge())
            .timeout(options.getTimeout())
            .rpId(options.getRpId())
            .allowCredentials(options.getAllowCredentials())
            .userVerification(options.getUserVerification())
            .extensions(options.getExtensions())
            .build();
        // @formatter:on
    }
}
