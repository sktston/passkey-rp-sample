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

import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDto;
import com.sktelecom.authentication.fido2.server.dto.registration.AttestationResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.PublicKeyCredentialTypeMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationResponseLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    uses = {
        AttestationResponseMapper.class,
        PublicKeyCredentialTypeMapper.class
    },
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthenticatorAttestationResponseServerRequestMapper {
    @Mapping(target = "clientExtensionResults", source = "getClientExtensionResults")
    PublicKeyCredentialDto<AttestationResponseDto> toWebauthnServerDto(
        AuthenticatorResponseServerRequest<AttestationResponse> rpServer);

    PublicKeyCredentialDto<AttestationResponseDto> toWebauthnServerDtoLv3(
        AuthenticatorResponseServerRequestLv3<AttestationResponseLv3> rpServer);
}
