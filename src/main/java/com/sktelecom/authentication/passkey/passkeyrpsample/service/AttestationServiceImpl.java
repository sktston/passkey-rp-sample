package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sktelecom.authentication.fido2.server.dto.common.ChallengeDto;
import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDto;
import com.sktelecom.authentication.fido2.server.dto.common.ServerResponseDto;
import com.sktelecom.authentication.fido2.server.dto.registration.AttestationResponseDto;
import com.sktelecom.authentication.fido2.server.dto.registration.PublicKeyCredentialCreationOptionsDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationResultsServerRequestDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration.AttestationOptionsMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration.AttestationOptionsServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration.AuthenticatorAttestationResponseServerRequestMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationResponseLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Attestation service implementation interacting with Webauthn server
 */
@RequiredArgsConstructor
@Service
public class AttestationServiceImpl implements AttestationService {
    private final WebAuthnRpRestClient webAuthnRpRestClient;
    private final ObjectMapper objectMapper;
    private final AttestationOptionsServerRequestMapper attestationOptionsServerRequestMapper;
    private final AttestationOptionsMapper attestationOptionsMapper;
    private final AuthenticatorAttestationResponseServerRequestMapper
        authenticatorAttestationResponseServerRequestMapper;

    /**
     * For conformance testing purpose
     */
    @Override
    public WebAuthnServerResponse<AttestationOptions> getOptions(AttestationOptionsServerRequest optionsRequest)
        throws WebAuthnServerException {
        // construct request for WebAuthn server
        RegistrationOptionsServerRequestDto requestDto =
            attestationOptionsServerRequestMapper.toWebauthnServerDto(optionsRequest);
        // call WebAuthn server and get response
        ServerResponseDto<ChallengeDto> response = webAuthnRpRestClient.getRegistrationOptions(requestDto);
        final String sessionId = response.getData().getTransactionId();
        final String options = response.getData().getOptions();

        // convert to PublicKeyCredentialCreationOptionsDto
        PublicKeyCredentialCreationOptionsDto webauthnServerOptions;
        try {
            webauthnServerOptions =
                objectMapper.readValue(options, PublicKeyCredentialCreationOptionsDto.class);
        } catch (JsonProcessingException e) {
            throw new WebAuthnServerException("Webauthn server option deserialization error", e);
        }

        // convert to AttestationOptions to interact with conformance tool and interop web app
        AttestationOptions attestationOptions = attestationOptionsMapper.toRpServerDto(webauthnServerOptions);
        // clear all received extensions from the WebAuthn server,
        // since the conformance tool client does not expect the such extensions
        attestationOptions.getExtensions().clear();
        if (optionsRequest.getExtensions() != null) {
            for (Map.Entry<String, Object> entry : optionsRequest.getExtensions().entrySet()) {
                attestationOptions.getExtensions().put(entry.getKey(), entry.getValue());
            }
        }

        return new WebAuthnServerResponse<>(sessionId, attestationOptions);
    }

    /**
     * For conformance testing purpose
     */
    @Override
    public void handleResult(AuthenticatorResponseServerRequest<AttestationResponse> result, String requestId)
        throws WebAuthnServerException {
        // construct request for WebAuthn server
        PublicKeyCredentialDto<AttestationResponseDto> publicKeyCredentialDto =
            authenticatorAttestationResponseServerRequestMapper.toWebauthnServerDto(result);
        RegistrationResultsServerRequestDto requestDto =
            new RegistrationResultsServerRequestDto(requestId, null, null, publicKeyCredentialDto);
        webAuthnRpRestClient.postRegistrationResponse(requestDto);
    }

    @Override
    public WebAuthnServerResponse<AttestationOptions> getLv3Options(AttestationOptionsServerRequestLv3 optionsRequest)
        throws WebAuthnServerException {
        // construct request for WebAuthn server
        RegistrationOptionsServerRequestDto requestDto =
            attestationOptionsServerRequestMapper.toWebauthnServerDtoLv3(optionsRequest);
        // call WebAuthn Server and get response
        ServerResponseDto<ChallengeDto> response = webAuthnRpRestClient.getRegistrationOptions(requestDto);
        final String sessionId = response.getData().getTransactionId();
        final String options = response.getData().getOptions();

        // convert to PublicKeyCredentialCreationOptionsDto
        PublicKeyCredentialCreationOptionsDto webauthnServerOptions;
        try {
            webauthnServerOptions =
                objectMapper.readValue(options, PublicKeyCredentialCreationOptionsDto.class);
        } catch (JsonProcessingException e) {
            throw new WebAuthnServerException("Webauthn server option deserialization error", e);
        }

        // convert to AttestationOptions to interact with conformance tool and interop web app
        AttestationOptions attestationOptions = attestationOptionsMapper.toRpServerDto(webauthnServerOptions);

        return new WebAuthnServerResponse<>(sessionId, attestationOptions);
    }

    @Override
    public void handleLv3Result(AuthenticatorResponseServerRequestLv3<AttestationResponseLv3> result, String requestId)
        throws WebAuthnServerException {
        // construct request for WebAuthn server
        PublicKeyCredentialDto<AttestationResponseDto> publicKeyCredentialDto =
            authenticatorAttestationResponseServerRequestMapper.toWebauthnServerDtoLv3(result);
        RegistrationResultsServerRequestDto requestDto =
            new RegistrationResultsServerRequestDto(requestId, null, null, publicKeyCredentialDto);
        webAuthnRpRestClient.postRegistrationResponse(requestDto);
    }
}
