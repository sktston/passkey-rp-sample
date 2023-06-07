package com.sktelecom.authentication.passkey.passkeyrpsample.service;

import com.sktelecom.authentication.passkey.passkeyrpsample.exception.WebAuthnServerException;
import java.util.List;

/**
 * WebAuthn enrolled user related service interface
 */
public interface UserService {
    /**
     * Delete user with a given user id
     * @param userId user id (user.id)
     * @return list of credential ids, if the user has no credential, the empty list will be returned
     * @throws WebAuthnServerException exception is thrown if anything is wrong
     */
    List<String> deleteUser(String userId) throws WebAuthnServerException;
}
