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

package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import com.sktelecom.authentication.fido2.server.validator.Base64UrlSafe;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.UserDeleteResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for a user
 */
@RequiredArgsConstructor
@RestController
@Validated
public class UserController {
    private final UserService userService;

    /**
     * Handler for deleting a user account and associated credentials in WebAuthn server
     * @param userId user identifier
     * @return deleted credentials' ids for the user
     */
    @DeleteMapping(path = "/users/{userId}")
    UserDeleteResponse deleteUser(@PathVariable @Base64UrlSafe String userId) {
        List<String> credentialIds = userService.deleteUser(userId);
        return UserDeleteResponse.builder().credentials(credentialIds).build();
    }
}
