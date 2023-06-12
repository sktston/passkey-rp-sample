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

import com.sktelecom.authentication.fido2.server.dto.registration.AuthenticatorSelectionCriteriaDto;
import com.sktelecom.authentication.fido2.server.dto.registration.ResidentKeyRequirementDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServerAuthenticatorSelectionCriteriaMapper {
    @Mapping(target = "requireResidentKey", source = "residentKey")
    ServerAuthenticatorSelectionCriteria toRpServerDto(AuthenticatorSelectionCriteriaDto webauthnServer);

    @Mapping(target = "residentKey", source = ".")
    AuthenticatorSelectionCriteriaDto toWebauthnServerDto(ServerAuthenticatorSelectionCriteria rpServer);

    default boolean toWebauthnRequireResidentKey(ResidentKeyRequirementDto residentKey) {
        if (residentKey == null) {
            return false;
        }
        return residentKey == ResidentKeyRequirementDto.REQUIRED;
    }

    default ResidentKeyRequirementDto getResidentKey(ServerAuthenticatorSelectionCriteria rpServer) {
        if (rpServer == null) {
            return null;
        }

        if (null != rpServer.getResidentKey()) {
            return ResidentKeyRequirementDto.find(rpServer.getResidentKey().getValue());
        }

        if (rpServer.isRequireResidentKey()) {
            return ResidentKeyRequirementDto.REQUIRED;
        }

        return null;
    }
}
