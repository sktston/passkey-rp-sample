/*
 * Copyright (C) 2023 SK TELECOM CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
