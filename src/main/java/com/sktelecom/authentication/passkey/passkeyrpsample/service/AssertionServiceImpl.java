package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sktelecom.authentication.fido2.server.dto.authentication.AssertionResponseDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.PublicKeyCredentialRequestOptionsDto;
import com.sktelecom.authentication.fido2.server.dto.common.ChallengeDto;
import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDto;
import com.sktelecom.authentication.fido2.server.dto.common.ServerResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication.AssertionOptionsMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication.AssertionOptionsServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication.AuthenticatorAssertionResponseServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AssertionOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Assertion service implementation interacting with Passkey Server
 */
@RequiredArgsConstructor
@Service
public class AssertionServiceImpl implements AssertionService {
    private final WebAuthnRpRestClient webAuthnRpRestClient;
    private final ObjectMapper objectMapper;
    private final AssertionOptionsServerRequestMapper assertionOptionsServerRequestMapper;
    private final AssertionOptionsMapper assertionOptionsMapper;
    private final AuthenticatorAssertionResponseServerRequestMapper authenticatorAssertionResponseServerRequestMapper;

    /**
     * For conformance testing purpose
     */
    @Override
    public WebAuthnServerResponse<AssertionOptions> getOptions(AssertionOptionsServerRequest optionsRequest)
        throws WebAuthnServerException {
        // construct request for webauthn server
        AuthenticationOptionsServerRequestDto requestDto =
            assertionOptionsServerRequestMapper.toWebauthnServerDto(optionsRequest);
        // call webauthn server and get response
        ServerResponseDto<ChallengeDto> response = webAuthnRpRestClient.getAuthenticationOptions(requestDto);
        final String sessionId = response.getData().getTransactionId();
        final String options = response.getData().getOptions();

        // convert to PublicKeyCredentialRequestOptionsDto
        PublicKeyCredentialRequestOptionsDto webauthnServerOptions;
        try {
            webauthnServerOptions =
                objectMapper.readValue(options, PublicKeyCredentialRequestOptionsDto.class);
        } catch (JsonProcessingException e) {
            throw new WebAuthnServerException("Webauthn server option deserialization error", e);
        }

        // convert to AssertionOptions to interact with conformance tool and interop web app
        AssertionOptions assertionOptions = assertionOptionsMapper.toRpServerDto(webauthnServerOptions);
        // clear all received extensions from the webauthn server,
        // since the conformance tool client does not expect the such extensions
        assertionOptions.getExtensions().clear();
        if (optionsRequest.getExtensions() != null) {
            for (Map.Entry<String, Object> entry : optionsRequest.getExtensions().entrySet()) {
                assertionOptions.getExtensions().put(entry.getKey(), entry.getValue());
            }
        }

        return new WebAuthnServerResponse<>(sessionId, assertionOptions);
    }

    /**
     * For conformance testing purpose
     */
    @Override
    public void handleResult(AuthenticatorResponseServerRequest<AssertionResponse> result, String requestId)
        throws WebAuthnServerException {
        // construct request for webauthn server
        PublicKeyCredentialDto<AssertionResponseDto> publicKeyCredentialDto =
            authenticatorAssertionResponseServerRequestMapper.toWebauthnServerDto(result);
        AuthenticationResultsServerRequestDto requestDto =
            new AuthenticationResultsServerRequestDto(requestId, null, null, publicKeyCredentialDto);
        webAuthnRpRestClient.postAuthenticationResponse(requestDto);
    }

    @Override
    public WebAuthnServerResponse<AssertionOptions> getLv3Options(AssertionOptionsServerRequestLv3 optionsRequest)
        throws WebAuthnServerException {
        // construct request for webauthn server
        AuthenticationOptionsServerRequestDto requestDto =
            assertionOptionsServerRequestMapper.toWebauthnServerDtoLv3(optionsRequest);
        // call webauthn server and get response
        ServerResponseDto<ChallengeDto> response = webAuthnRpRestClient.getAuthenticationOptions(requestDto);
        final String sessionId = response.getData().getTransactionId();
        final String options = response.getData().getOptions();

        // convert to PublicKeyCredentialRequestOptionsDto
        PublicKeyCredentialRequestOptionsDto webauthnServerOptions;
        try {
            webauthnServerOptions =
                objectMapper.readValue(options, PublicKeyCredentialRequestOptionsDto.class);
        } catch (JsonProcessingException e) {
            throw new WebAuthnServerException("Webauthn server option deserialization error", e);
        }

        // convert to AssertionOptions to interact with conformance tool and interop web app
        AssertionOptions assertionOptions = assertionOptionsMapper.toRpServerDto(webauthnServerOptions);

        return new WebAuthnServerResponse<>(sessionId, assertionOptions);
    }

    @Override
    public void handleLv3Result(AuthenticatorResponseServerRequestLv3<AssertionResponse> result, String requestId)
        throws WebAuthnServerException {
        // construct request for webauthn server
        PublicKeyCredentialDto<AssertionResponseDto> publicKeyCredentialDto =
            authenticatorAssertionResponseServerRequestMapper.toWebauthnServerDtoLv3(result);
        AuthenticationResultsServerRequestDto requestDto =
            new AuthenticationResultsServerRequestDto(requestId, null, null, publicKeyCredentialDto);
        webAuthnRpRestClient.postAuthenticationResponse(requestDto);
    }
}
