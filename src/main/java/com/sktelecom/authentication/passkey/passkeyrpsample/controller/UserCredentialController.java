package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoDto;
import com.sktelecom.authentication.fido2.server.validator.Base64UrlSafe;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.GetUserCredentialResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.GetUserCredentialsResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.ServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.UserCredentialService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for user's credentials
 */
@RequiredArgsConstructor
@RestController
@Validated
public class UserCredentialController {
    private final UserCredentialService userCredentialService;

    @GetMapping(path = "/users/{userId}/credentials/{credentialId}")
    GetUserCredentialResponse getUserCredential(@PathVariable @Base64UrlSafe String userId,
        @PathVariable @Base64UrlSafe String credentialId) {
        final CredentialInfoDto credential = userCredentialService.getUserCredential(userId, credentialId);
        return GetUserCredentialResponse.builder()
            .credential(credential)
            .build();
    }

    @GetMapping(path = "/users/{userId}/credentials")
    GetUserCredentialsResponse getUserCredentials(@PathVariable @Base64UrlSafe String userId) {
        final List<CredentialInfoDto> credentials = userCredentialService.getUserCredentials(userId);
        return GetUserCredentialsResponse.builder()
            .credentials(credentials)
            .build();
    }

    @PatchMapping(path = "/users/{userId}/credentials/{credentialId}/activate")
    ServerResponse activateUserCredential(@PathVariable @Base64UrlSafe String userId,
        @PathVariable @Base64UrlSafe String credentialId) {
        userCredentialService.activateUserCredential(userId, credentialId);
        return new ServerResponse(Status.OK, "");
    }

    @PatchMapping(path = "/users/{userId}/credentials/{credentialId}/deactivate")
    ServerResponse deactivateUserCredential(@PathVariable @Base64UrlSafe String userId,
        @PathVariable @Base64UrlSafe String credentialId) {
        userCredentialService.deactivateUserCredential(userId, credentialId);
        return new ServerResponse(Status.OK, "");
    }

    @DeleteMapping(path = "/users/{userId}/credentials/{credentialId}")
    ServerResponse removeUserCredential(@PathVariable @Base64UrlSafe String userId,
        @PathVariable @Base64UrlSafe String credentialId) {
        userCredentialService.removeUserCredential(userId, credentialId);
        return new ServerResponse(Status.OK, "");
    }
}
