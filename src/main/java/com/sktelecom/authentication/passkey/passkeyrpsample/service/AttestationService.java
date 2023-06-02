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
 * Attestation (passkey authentication) service interface
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
