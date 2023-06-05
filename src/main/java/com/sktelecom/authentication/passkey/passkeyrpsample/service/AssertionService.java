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
