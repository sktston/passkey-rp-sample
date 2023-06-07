package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.fido2.server.dto.common.CredentialStatusDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialIdDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoListResponseDto;
import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Credentials associated to the user related service implementation
 */
@RequiredArgsConstructor
@Service
public class UserCredentialServiceImpl implements UserCredentialService {
    private final WebAuthnRpRestClient webAuthnRpRestClient;

    @Override
    public CredentialInfoDto getUserCredential(String userId, String credentialId) throws WebAuthnServerException {
        final CredentialInfoResponseDto data = webAuthnRpRestClient.getUserCredential(userId, credentialId).getData();
        return data.getCredential();
    }

    @Override
    public List<CredentialInfoDto> getUserCredentials(String userId) throws WebAuthnServerException {
        final CredentialInfoListResponseDto data = webAuthnRpRestClient.getUserCredentials(userId).getData();
        return data.getCredentials();
    }

    @Override
    public void activateUserCredential(String userId, String credentialId) throws WebAuthnServerException {
        webAuthnRpRestClient.updateCredentialStatus(userId, credentialId, CredentialStatusDto.ACTIVE);
    }

    @Override
    public void deactivateUserCredential(String userId, String credentialId) throws WebAuthnServerException {
        webAuthnRpRestClient.updateCredentialStatus(userId, credentialId, CredentialStatusDto.INACTIVE);
    }

    @Override
    public String removeUserCredential(String userId, String credentialId) throws WebAuthnServerException {
        final CredentialIdDto data = webAuthnRpRestClient.deleteCredential(userId, credentialId).getData();
        return data.getCredentialId();
    }
}
