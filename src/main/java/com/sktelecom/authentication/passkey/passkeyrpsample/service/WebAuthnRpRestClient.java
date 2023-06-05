package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultDto;
import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationResultsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.common.ChallengeDto;
import com.sktelecom.authentication.fido2.server.dto.common.ServerResponseDto;
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
}
