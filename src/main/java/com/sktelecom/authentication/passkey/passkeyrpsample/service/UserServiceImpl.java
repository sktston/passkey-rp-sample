package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * WebAuthn enrolled user related service implementation
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final WebAuthnRpRestClient webAuthnRpRestClient;

    @Override
    public List<String> deleteUser(String userId) throws WebAuthnServerException {
        return webAuthnRpRestClient.deleteUser(userId).getData().getCredentialIds();
    }
}
