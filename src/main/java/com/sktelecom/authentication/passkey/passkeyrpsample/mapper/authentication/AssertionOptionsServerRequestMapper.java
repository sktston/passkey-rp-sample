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

package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication;

import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AssertionOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.util.UserIdGeneratorUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssertionOptionsServerRequestMapper {
    @Mapping(target = "userId", source = "username")
    @Mapping(target = "timeout", constant = "300000L")
    AuthenticationOptionsServerRequestDto toWebauthnServerDto(AssertionOptionsServerRequest rpServer);

    @Mapping(target = "userId", source = "username")
    AuthenticationOptionsServerRequestDto toWebauthnServerDtoLv3(AssertionOptionsServerRequestLv3 rpServer);

    default String toWebauthnUserId(String username) {
        if (username == null) {
            return null;
        }
        return UserIdGeneratorUtil.generate(username);
    }
}
