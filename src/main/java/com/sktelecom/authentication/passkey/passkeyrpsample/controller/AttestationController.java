package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication.AssertionOptionsServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration.AttestationOptionsServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.ServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationResponseLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.AttestationService;
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
 * A controller for registration process
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class AttestationController {
    private static final String COOKIE_NAME = "attestation_request_id";
    private final AttestationService attestationService;

    /**
     * For conformance testing purpose
     */
    @PostMapping(path = "/attestation/options")
    AttestationOptionsServerResponse registrationChallenge(
        @Valid @RequestBody AttestationOptionsServerRequest request, HttpServletResponse httpServletResponse) {
        WebAuthnServerResponse<AttestationOptions> optionsResponse = attestationService.getOptions(request);
        AttestationOptions options = optionsResponse.getOptions();
        // set cookie to track request and subsequent response
        CookieUtil.addCookie(httpServletResponse, COOKIE_NAME, optionsResponse.getSessionId());
        return convert(options);
    }

    /**
     * For conformance testing purpose
     */
    @PostMapping(path = "/attestation/result")
    ServerResponse registrationResponse(@CookieValue(name = COOKIE_NAME) String requestId,
        @Valid @RequestBody AuthenticatorResponseServerRequest<AttestationResponse> result) {
        attestationService.handleResult(result, requestId);
        return new ServerResponse(Status.OK, "");
    }

    /**
     * Handler to start webauthn registration process
     * @param request parameters for registration options, you may use your own model,
     *                most of the cases, such values are populated in the backend.
     *                Username and user id values should be obtained from the authenticated session.
     *                For simplicity, this sample application generates the user id with username.
     *                In general, the services might have their own user identifier.
     *                The user id generation for the sample application is handled during model mapping.
     *                Check this {@link AttestationOptionsServerRequestMapper#toWebauthnUserDto(AttestationOptionsServerRequestLv3 rpServer) model mapper }.
     * @param httpServletResponse servlet response
     * @return registration options
     */
    @PostMapping(path = "/lv3/attestation/options")
    AttestationOptionsServerResponse registrationChallengeLv3(
        @Valid @RequestBody AttestationOptionsServerRequestLv3 request, HttpServletResponse httpServletResponse) {
        WebAuthnServerResponse<AttestationOptions> optionsResponse = attestationService.getLv3Options(request);
        AttestationOptions options = optionsResponse.getOptions();

        // You might have different way to manage the passkey registration session (transaction)
        // set cookie to track request and subsequent response for simplicity
        CookieUtil.addCookie(httpServletResponse, COOKIE_NAME, optionsResponse.getSessionId());
        return convert(options);
    }

    /**
     * Handler for handling registration response from the client
     * @param requestId session id indicating the corresponding registration option
     * @param result registration response from the client
     * @return registration result, you may use your own model
     */
    @PostMapping(path = "/lv3/attestation/result")
    ServerResponse registrationResponseLv3(@CookieValue(name = COOKIE_NAME) String requestId,
        @Valid @RequestBody AuthenticatorResponseServerRequestLv3<AttestationResponseLv3> result) {
        attestationService.handleLv3Result(result, requestId);
        return new ServerResponse(Status.OK, "");
    }

    private AttestationOptionsServerResponse convert(AttestationOptions options) {
        if (options != null) {
            // @formatter:off
            return AttestationOptionsServerResponse.builder()
                .rp(options.getRp())
                .user(options.getUser())
                .challenge(options.getChallenge())
                .pubKeyCredParams(options.getPubKeyCredParams())
                .timeout(options.getTimeout())
                .excludeCredentials(options.getExcludeCredentials())
                .authenticatorSelection(options.getAuthenticatorSelection())
                .attestation(options.getAttestation())
                .extensions(options.getExtensions())
                .status(Status.OK)
                .build();
            // @formatter:on
        }
        return null;
    }
}
