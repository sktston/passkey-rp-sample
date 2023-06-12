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

package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.registration.UserDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.util.UserIdGeneratorUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    ServerAuthenticatorSelectionCriteriaMapper.class
})
public interface AttestationOptionsServerRequestMapper {
    @Mapping(target = "user", source = "rpServer")
    @Mapping(target = "excludeCredentials", constant = "true")
    @Mapping(target = "timeout", constant = "300000L")
    RegistrationOptionsServerRequestDto toWebauthnServerDto(AttestationOptionsServerRequest rpServer);

    @Mapping(target = "user", source = "rpServer")
    RegistrationOptionsServerRequestDto toWebauthnServerDtoLv3(AttestationOptionsServerRequestLv3 rpServer);

    default UserDto toWebauthnUserDto(AttestationOptionsServerRequest rpServer) {
        if (rpServer == null || rpServer.getUsername() == null) {
            return null;
        }
        String userId = UserIdGeneratorUtil.generate(rpServer.getUsername());
        return new UserDto(userId, rpServer.getUsername(), rpServer.getDisplayName());
    }

    default UserDto toWebauthnUserDto(AttestationOptionsServerRequestLv3 rpServer) {
        if (rpServer == null || rpServer.getUsername() == null) {
            return null;
        }
        String userId = UserIdGeneratorUtil.generate(rpServer.getUsername());
        return new UserDto(userId, rpServer.getUsername(), rpServer.getDisplayName());
    }
}
